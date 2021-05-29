/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import action.SendMail;
import action.AcknowlegeMail;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;

/**
 *
 * @author samiksha.t
 */
@WebServlet(name = "send_mail", urlPatterns = {"/send_mail"})

public class send_mail extends HttpServlet {

    private Object inputStream;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        System.out.println("inside mail");
        boolean flag = false;

        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");

        String email = request.getParameter("email");
        String contact = request.getParameter("contact");

        String message = request.getParameter("message");;

        System.out.println("name.." + name);
        System.out.println("email.." + email);

        System.out.println("contact.." + contact);
        System.out.println("message.." + message);

        String passwd = "anuabhiprasad";
        String from = "anu12.prasad@gmail.com";

        System.out.println("from,," + from);
        String recipient = "";
        recipient = "anu12.prasad@gmail.com";
        System.out.println("recipient" + recipient);
        String recipient2 = email;
        String subject = "";
        String subject2 = "Acknowledgment of submission of form in Smile Baby of request for Nanny";
        String content = " Hello Team,<br>";
        content +=  "Below are the details of request:<br>";
        content += "";
        String content2 = " Hello "+ name +",<br>";
        content2 += "<br>Thank you for showing interest in Smile Baby. We have received your request for booking a Nanny for you baby.<br>";
        content2 += "Our representative will shortly contact you on your number :"+ contact;
        content2 += "<br>Happy Parenting.<br>";
        content2 += "<br>Thanks & Regards,";
        content2 += "<br>Team Smile Baby<br>";
        
        subject = "Details of request for Nanny";

        content += "<div>";

        content += "<table border=3 align=center style=margin-top:20px;>";
        content += "<thead>";

        content += "<tr>";
        content += "<th style='border:2px solid black; text-align:left; background-color:#E8E8E8;'>Name</th>";
        content += "<td colspan=6; style='border:2px solid black; text-align:left;' >" + name + "</td>";

        content += "</tr>";

        content += "<tr>";
        content += "<th style='border:2px solid black; text-align:left; background-color:#E8E8E8;'>Email</th>";
        content += "<td colspan=6>" + email + "</td>";
        content += "</tr>";
        content += "<tr>";
        content += "<th style='border:2px solid black; text-align:left; background-color:#E8E8E8;'>Contact</th>";
        content += "<td colspan=6>" + contact + "</td>";

        content += "</tr>";
        content += "<tr>";
        content += "<th style='border:2px solid black; text-align:left; background-color:#E8E8E8;'>Message</th>";
        content += "<td colspan=6; style='border:2px solid black; text-align:left;' >" + message + "</td>";

        content += "</tr>";

        content += "</tbody>";

        content += "</table>";

        content += "</div>";

        try {

            SendMail.sendMail( from, passwd, recipient, subject, content);
            AcknowlegeMail.ackMail( from, passwd, recipient2, subject2, content2);

        } catch (MessagingException ex) {
            Logger.getLogger(send_mail.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.sendRedirect("index.jsp?res=Done");

    }
}