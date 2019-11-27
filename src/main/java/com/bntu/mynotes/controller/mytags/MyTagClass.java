package com.bntu.mynotes.controller.mytags;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class MyTagClass extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {
        getJspContext().getOut().write("Our Contact info: +3752913342800; Minsk, Luyban, Kupalovcki lain, 5/44");
    }
}
