package net.mybluemix.rest;

import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;  
import javax.ws.rs.POST;
import javax.ws.rs.Path;  
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.google.gson.Gson;

import net.mybluemix.dao.FornecedorDAO;
import net.mybluemix.dao.LoteDAO;
import net.mybluemix.dao.MateriaPrimaDAO;
import net.mybluemix.dao.ReceitaDAO;
import net.mybluemix.entity.Fornecedor;
import net.mybluemix.entity.Lote;
import net.mybluemix.entity.MateriaPrima;
import net.mybluemix.entity.Receita;


@Path("receita")
@Produces(MediaType.APPLICATION_JSON)

public class ReceitaWS {

    @GET
    public List<Receita> ReceitaList(){
    	ReceitaDAO mpd = new ReceitaDAO();
    	List<Receita> mpl = mpd.findAll();
    	return  mpl;
    }
    
    @GET
    @Path("{sku}")
    public Receita  Receita(@PathParam("sku") Long sku){
    	ReceitaDAO mpd = new ReceitaDAO();
    	return mpd.find(sku);
    }


    @POST
	@Path("/create")
	@Consumes("application/json")
    public void create(String json) throws Exception{
    	Gson gson = new Gson();
		Receita mp =  gson.fromJson(json, Receita.class);
    	ReceitaDAO mpd = new ReceitaDAO();
    	mpd.create(mp);
    }	
    
    @POST
	@Path("/update")
	@Consumes("application/json")
    public void update(String json) throws Exception{
    	Gson gson = new Gson();
		Receita f=  gson.fromJson(json, Receita.class);	
    	ReceitaDAO fd = new ReceitaDAO();
    	fd.update(f, f.getSku());
    }
    

   



}