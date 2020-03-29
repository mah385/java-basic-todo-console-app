package com.mah.shared;

import static com.mah.shared.AnsiColorUtils.*;

public class MyResponseEntity {

    private String message;

    public MyResponseEntity() {
    }

    public MyResponseEntity(String message) {
        this.message = message;
    }

    private String displayMyResponseEntity() {
        return ANSI_CYAN + "Message: "+ ANSI_RESET + this.message ;
    }

    @Override
    public String toString() {
        return displayMyResponseEntity();
    }
}
