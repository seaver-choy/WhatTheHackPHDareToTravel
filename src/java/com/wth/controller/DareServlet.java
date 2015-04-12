/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wth.controller;


import com.wth.model.MySQLDarePhotoDAO;
import com.wth.model.Photo;
import com.wth.model.PhotoDAO;
import com.wth.model.Dare;
import com.wth.model.DareDAO;
import com.wth.model.MySQLDareDAO;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;



/**
 *
 * @author Kurt
 */
@WebServlet(name = "DareServlet", urlPatterns = {"/DareServlet"})
public class DareServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		FileItem itemToSave = null;
                Dare d = new Dare();
                try{
                d.setUsername((String) session.getAttribute("username"));
                d.setDareName((String) request.getParameter("dareName"));
                d.setDescription((String) request.getParameter("description"));
                d.setInstruction((String) request.getParameter("instructions"));
                d.setPostDate(LocalDateTime.now());
                d.setLocID(1);
                DareDAO dareDAO = new MySQLDareDAO();
                d.setDareID(dareDAO.addDare(d));
                }catch(Exception e){
                    e.printStackTrace();
                }
		if (session != null) {
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);

			if (isMultipart) {
			    DiskFileItemFactory factory = new DiskFileItemFactory();
			    factory.setFileCleaningTracker(null);
			    ServletFileUpload upload = new ServletFileUpload(factory);

			    try {
			        List items = upload.parseRequest(request);
			        Iterator iterator = items.iterator();

			        while (iterator.hasNext()) {
			            FileItem item = (FileItem) iterator.next();
			            if (!item.isFormField()) {
			            	itemToSave = item;
			            } else {
			            }
			        }
	                String fileName = itemToSave.getName();

	                String root = getServletContext().getRealPath("");
	                File path = new File(root + "/uploaded_photos");
	                if (!path.exists()) {
	                    boolean status = path.mkdirs();
                        }
                        PhotoDAO photoDAO = new MySQLDarePhotoDAO();
                        Photo p = new Photo(fileName);
                        photoDAO.addPhoto(d.getDareID(), p);
	                File uploadedFile = new File(path + "/" + fileName);
	                itemToSave.write(uploadedFile);
			    } catch (Exception e) {
			    	
			    }
			}
                        
			response.sendRedirect("index.jsp");
			
		}

	}
}
