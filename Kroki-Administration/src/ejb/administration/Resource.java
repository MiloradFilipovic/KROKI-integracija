package ejb.administration;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import framework.AbstractEntity;

@Entity
@Table(name = "Resource")
public class Resource extends AbstractEntity implements Serializable {  

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
		@Column(name="name", unique = false, nullable = false)
		private String name;
         
		@Column(name="link", unique = false, nullable = false)
		private String link;
		
		@Column(name="paneltype", unique = false, nullable = false)
		private String paneltype;
         
    	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "resource")
  		private Set<Permission> permission = new HashSet<Permission>();
         

	public Resource(){
	}

    	public void addPermission(Permission entity) {
    		if(entity != null) {
    			if(!permission.contains(entity)) {
    				entity.setResource(this);
    				permission.add(entity);
    			}
    		}
    	}
    	
    	public void removePermission(Permission entity) {
    		if(entity != null) {
    			if(permission.contains(entity)) {
					entity.setResource(null);
    				permission.remove(entity);
    			}
    		}
    	}

      public String getName(){
           return name;
      }
      
      public void setName(String name){
           this.name = name;
      }
      
      public String getLink(){
           return link;
      }
      
      public void setLink(String link){
           this.link = link;
      }
      
      public String getPaneltype() {
		return paneltype;
	}

	public void setPaneltype(String paneltype) {
		this.paneltype = paneltype;
	}

	public Set<Permission> getPermission(){
           return permission;
      }
      
      public void setPermission( Set<Permission> permission){
           this.permission = permission;
      }
      

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public Object[] getValues() {	
		List<Object> list = new ArrayList<Object>();
		
		list.add(id);		
		list.add(name.toString());
		list.add(link.toString());
		list.add(paneltype.toString());
		 
		 return list.toArray();
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		result.append(name + " ");
		result.append(link + " ");
		result.append(paneltype + " ");
		
		return result.toString();
	}

}
