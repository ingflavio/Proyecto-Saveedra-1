package com.springJWT.yop.Service.impl;


import com.springJWT.yop.Persistens.Colores;
import com.springJWT.yop.Repository.ColoresRepository;
import com.springJWT.yop.Service.ServiceColores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceColoresImpl implements ServiceColores {

    @Autowired
    private ColoresRepository coloresRepository;

    @Override
    public Optional<Colores> findById(Long id) {
        return coloresRepository.findById(id);
    }

    @Override
    public Colores guardar(Colores colores) {
        return coloresRepository.save(colores);
    }

    @Override
    public Colores actualizarColores(Long id, Colores colores) {
        Optional<Colores> coloresOptional = coloresRepository.findById(id);
        if (coloresOptional.isPresent()) {
            Colores coloresExistente = coloresOptional.get();
            coloresExistente.setPrimary_color(colores.getPrimary_color());
            coloresExistente.setSecondary(colores.getSecondary());
            coloresExistente.setAccent(colores.getAccent());
            coloresExistente.setButton(colores.getButton());
            coloresExistente.setTitleSize(colores.getTitleSize());
            coloresExistente.setSubtitleSize(colores.getSubtitleSize());
            coloresExistente.setParagraphSize(colores.getParagraphSize());
            coloresExistente.setTitleFontPath(colores.getTitleFontPath());
            coloresExistente.setParagraphFontPath(colores.getParagraphFontPath());
            return coloresRepository.save(coloresExistente);
        } else {
            return null;
        }
    }

    @Override
    public void eliminarColores(Long id) {
        coloresRepository.deleteById(id);
    }
}
