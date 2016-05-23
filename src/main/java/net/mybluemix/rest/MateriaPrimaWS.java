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
import net.mybluemix.entity.Fornecedor;
import net.mybluemix.entity.Lote;
import net.mybluemix.entity.MateriaPrima;


@Path("materiaprima")
@Produces(MediaType.APPLICATION_JSON)

public class MateriaPrimaWS {

    @GET
    public List<MateriaPrima> MateriaPrimaList(){
    	MateriaPrimaDAO mpd = new MateriaPrimaDAO();
    	List<MateriaPrima> mpl = mpd.findAll();
    	return  mpl;
    }
    
    @GET
    @Path("{sku}")
    public MateriaPrima  MateriaPrima(@PathParam("sku") String sku){
    	int x = Integer.parseInt(sku);
    	MateriaPrimaDAO mpd = new MateriaPrimaDAO();
    	return mpd.find(x);
    }


    @POST
	@Path("/create")
	@Consumes("application/json")
    public void create(String json) throws Exception{
    	Gson gson = new Gson();
		MateriaPrima mp =  gson.fromJson(json, MateriaPrima.class);
    	MateriaPrimaDAO mpd = new MateriaPrimaDAO();
    	mpd.create(mp);
    }
    
    @POST
	@Path("/update")
	@Consumes("application/json")
    public void update(String json) throws Exception{
    	Gson gson = new Gson();
		MateriaPrima f=  gson.fromJson(json, MateriaPrima.class);	
    	MateriaPrimaDAO fd = new MateriaPrimaDAO();
    	fd.update(f, f.getSku());
    }
    

    @POST
	@Path("/lote/add/{sku}")
	@Consumes("application/json")
    public void addLot(String json, @PathParam("sku") Integer sku) throws Exception{
    	Long lsku  = new Long(sku);
    	Gson gson = new Gson();
		Lote lote =  gson.fromJson(json, Lote.class);
		LoteDAO ldao = new LoteDAO();
		ldao.create(lote);
    	MateriaPrimaDAO mpd = new MateriaPrimaDAO();
    	MateriaPrima mp = mpd.find(sku);
    	if(mp!=null)
    		mp.addLotes(lote);
    	mpd.update(mp, lsku);
    }



}
