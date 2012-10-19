package adapt.resources;

import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.restlet.Context;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.data.Request;
import org.restlet.data.Response;
import org.restlet.resource.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.Variant;

import adapt.application.AdaptApplication;
import adapt.entities.User;

public class IndexResource extends BaseResource {

	Map<String, Object> dataModel = new TreeMap<String, Object>();
	
	public IndexResource(Context context, Request request, Response response) {
		super(context, request, response);
		setModifiable(true);
		getVariants().add(new Variant(MediaType.TEXT_HTML));
	}
	
	@Override
	public void handleGet() {
		Form form = getRequest().getEntityAsForm();
		String username = form.getFirstValue("username");
		String password = form.getFirstValue("password");
		if(username != null && password != null) {
			register(username, password);
		}
		super.handleGet();
	}

	public User register(String username, String password) {
		AdaptApplication app = (AdaptApplication) getApplication();
		EntityManager em = app.getEmf().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		User u = new User();
		u.setName(username);
		u.setPassword(password);
		try {
			em.persist(u);
			tx.commit();
			em.close();
			String msg = "Uspe&#154;no ste se registrovali! Sada se mo&#382;ete ulogovati";
			String clr = "blue";
			dataModel.put("msg", msg);
			dataModel.put("clr", clr);
			return u;
		} catch (Exception e) {
			String msg = "Do&#154;lo je do gre&#154;ke prilikom registracije, molimo poku&#154;ajte ponovo.";
			String clr = "red";
			dataModel.put("msg", msg);
			dataModel.put("clr", clr);
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
    public Representation represent(Variant variant) throws ResourceException {
        //dataModel  = new TreeMap<String, Object>();
        return getHTMLTemplateRepresentation("index.html", dataModel);
    }

	@Override
	public void handlePost() {
		handleGet();
	}
}