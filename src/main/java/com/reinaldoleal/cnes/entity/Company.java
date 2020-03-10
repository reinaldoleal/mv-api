package com.reinaldoleal.cnes.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Company {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	@Column(nullable = false)
    private String cnes;
	
	@Column()
    private String noFantasia;
	
	@Column(nullable = false)
    private String noEmpresarial;
	
	@Column(nullable = false)
    private String uf;
	
	@Column(nullable = false)
    private String noMunicipio;	
	
	@Column()
    private String gestao;
	
	@Column()
    private Integer natJuridica;
	
	@Column()
    private String atendeSus;

}
