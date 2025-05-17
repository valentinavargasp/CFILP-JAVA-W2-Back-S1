package com.alkemy.wallet.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "virtual_payment")
public class VirtualPayment {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payment")
    	private int id;

    	@Column(name = "id_transaction")
    	private int idTransaction;

    	@Column(name = "commerce_name")
    	private String commerceName;

        //relaciones 
	//@OneToMany(mappedBy = "commerceName")
	//@JsonBackReference
	
	//private List<Transaction> transactions;

    //constructores
    public VirtualPayment(){

    }

    public VirtualPayment(int id, int idTransaction, String commerceName){
        this.id = id;
        this.idTransaction = idTransaction;
        this.commerceName = commerceName;

    }
    @Override
    public String toString(){
        return "VirtualPayment {" +
            "id=" + id +
            "idTransaction=" + idTransaction +
            "commerceName=" + commerceName +
       "}";
    }
}
