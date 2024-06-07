package com.Practica1.ResourceServer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.Practica1.ResourceServer.Cypher.decrypt2;
import static com.Practica1.ResourceServer.Cypher.encrypt2;


@RestController
@RequestMapping("api/cypher")

public class CypherController {

    @GetMapping("{cadena}")
    public String cypherString(@PathVariable("cadena") String cadena ) throws Exception {
        String key = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456"; //Llave

        byte[] encrypted = encrypt2(cadena.getBytes(), "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456".getBytes());
        byte[] origText = decrypt2(new String(encrypted).getBytes(), "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456".getBytes());
        return  "Cadena Encriptada: " + new String(encrypted) + "    Cadena desencriptada: "+new String(origText);
    }
}
