// cSpell:ignore servlet
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author tutaa
 */
public class MiServlet extends HttpServlet {
	private String rutaArchivo = "";

	/**
	 * Processes requests for both HTTP <code>GET</code> and
	 * <code>POST</code> methods.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	public void escribirEnArchivo(String infoGrabar) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
			bw.write(infoGrabar);
			bw.newLine();
			bw.flush();

		} catch (IOException error) {
			System.out.println("Error E/S: " + String.valueOf(error));
		}
	}

	public String leerArchivo() {
		StringBuilder contenido = new StringBuilder();
		String linea;

		try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
			while ((linea = br.readLine()) != null) {
				contenido.append(linea).append("<br>");
			}
		} catch (IOException error) {

			return ("Error E/S: " + String.valueOf(error));
		}

		return contenido.toString();
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		rutaArchivo = request.getParameter("rutaArchivo");
		this.escribirEnArchivo(request.getParameter("contenido"));
		try (PrintWriter out = response.getWriter()) {
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Respuesta</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Información en archivo: </h1>");
			out.println("<h4> " + this.leerArchivo() + " </h4>");
			out.println("</body>");
			out.println("</html>");
		}
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
	// + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
