package com.fvr._comun;

import java.io.File;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * @author Emilio Estecha 2013
 *
 */
public class Upload {
    public Upload() {}
    public void proceso(HttpServletRequest request, HttpServletResponse response, String dir) {
        
        String nombre = "";
        
        if (ServletFileUpload.isMultipartContent(request)){
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                Iterator<FileItem> iter = null;
                @SuppressWarnings("unchecked")
				java.util.List<FileItem> items = upload.parseRequest(request);
                // Primero busco el nombre de archivo destino:
                iter = items.iterator();
                while ( iter.hasNext() ) {
                    FileItem item = iter.next();
                    if (item.isFormField() ) {
                        String s = processFormField(item);
                        if ( s.trim().length() > 0 ) nombre = s;
                    }
                }
                // Ahora busco el "archivo" en si mismo:
                iter = items.iterator();
                while ( iter.hasNext() ) {
                    FileItem item = iter.next();
                    if ( !item.isFormField() ) {
                        processUploadedFile( item, dir, nombre );
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    private String processFormField(FileItem item) {
        String resultado = "";
        
        String nombre = item.getFieldName();
        int idx = nombre.lastIndexOf("\\");
        if (idx>-1) nombre = nombre.substring(idx+1);
        if ( nombre.trim().equalsIgnoreCase("nombreDestino") )
            resultado = item.getString();
        
        return resultado;
    }
    private void processUploadedFile(FileItem item, String dir, String nombreExterno) throws Exception {
        String nombre = item.getName();
        int idx = nombre.lastIndexOf("\\");
        if (idx>-1) nombre = nombre.substring(idx+1);
        
        if ( nombreExterno != null & nombreExterno.trim().length() > 0 )
            nombre = nombreExterno;
        
        new File(dir).mkdirs();
        
        File archivoDestino = new File(dir + "//" + nombre );
        item.write( archivoDestino );
    }
}
