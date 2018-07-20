package com.spring.cxf.security;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.ws.rs.core.Response;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxrs.ext.RequestHandler;
import org.apache.cxf.jaxrs.model.ClassResourceInfo;
import org.apache.cxf.jaxrs.utils.HttpUtils;
import org.apache.cxf.message.Message;
import org.springframework.beans.factory.annotation.Value;

import com.spring.cxf.exception.AppException;

public class TokenSecurityFilter implements RequestHandler {

	private String[] paths;

	private String tsec;

	private String rawPath;
	
	@Value("${tsec.not.present.msg}")
	private String tsecNotPresent;
	
//	@Value("#{${users}}")
	private Map<String, String> map;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Response handleRequest(Message m, ClassResourceInfo resourceClass) {
		Map<String, List> headers = (Map<String, List>) m.get(Message.PROTOCOL_HEADERS);
		tsec = headers.get("tsec") != null ? headers.get("tsec").get(0).toString() : null;
		rawPath = HttpUtils.getPathToMatch(m, true);
		if (tsec != null)
			validate();
		else
			validate();

		return null;

	}

	private void  validate()   {
		
		for(String path : paths) {
			if(rawPath.contains(path) && tsec == null) {
				throw new Fault(new AppException(403, tsecNotPresent));
						
			}else {
				//TODO:: Service para validar tsec
			}
		}
	}

	public String[] getPaths() {
		return paths;
	}

	public void setPaths(String[] paths) {
		this.paths = paths;
	}

	public String getTsecNotPresent() {
		return tsecNotPresent;
	}

	public void setTsecNotPresent(String tsecNotPresent) {
		this.tsecNotPresent = tsecNotPresent;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	
	/**
	 * Divide una cadena por el separador indicado, colocando cada uno de los
	 * elementos dentro de una lista. Hace la conversión de cada elemento
	 * segun la clase (soportada) especificada.
	 * por ejemplo:    Utils.explode("," , "1,54", java.lang.Integer.class);
	 * generará una Lista con 2 elementos, los cuales serán almacenados
	 * como objetos java.lang.Integer
	 *
	 * @param separador Separador usado para dividir la cadena
	 * @param cadena Cadena a dividir
	 * @param clase Nombre completo de la clase:
	 * 		Valores soportados:
	 *       	java.lang.Integer.class - Para generar elementos Integer
	 *          java.lang.Long.class - Para generar elementos Long
	 *          java.lang.Double.class - Para generar elementos Double
	 *          java.lang.Float.class - Para generar elementos Float
	 *          java.math.BigInteger.class - Para generar elementos BigInteger
	 *          java.math.BigDecimal.class - Para generar elementos BigDecimal
	 * 		Si el nombre de clase no es soportado lo maneja como Cadena
	 * @return lista con los valores divididos.
	 *
	 */
	public static List explode(String separador, String cadena, Class clase) {
		List vDatos = new ArrayList();

		if (cadena == null) {
			return vDatos;
		}

		StringTokenizer st = new StringTokenizer(cadena, separador);
		int i = 0;
		while (st.hasMoreTokens()) {
			Object obj = null;
			String valor = st.nextToken().trim();
			if (clase.getName().equals("java.lang.Integer")) {
				if (valor == null || valor.equals("")) {
					obj = Integer.class;
				} else {
					obj = new Integer(valor);
				}
			} else if (clase.getName().equals("java.lang.Long")) {
				if (valor == null || valor.equals("")) {
					obj = Long.class;
				} else {
					obj = new Long(valor);
				}
			} else if (clase.getName().equals("java.lang.Double")) {
				if (valor == null || valor.equals("")) {
					obj = Double.class;
				} else {
					obj = new Double(valor);
				}
			} else if (clase.getName().equals("java.lang.Float")) {
				if (valor == null || valor.equals("")) {
					obj = Float.class;
				} else {
					obj = new Float(valor);
				}
			} else if (clase.getName().equals("java.math.BigInteger")) {
				if (valor == null || valor.equals("")) {
					obj = BigInteger.class;
				} else {
					obj = new BigInteger(valor);
				}
			} else if (clase.getName().equals("java.math.BigDecimal")) {
				if (valor == null || valor.equals("")) {
					obj = BigDecimal.class;
				} else {
					obj = new BigDecimal(valor);
				}
			} else { //java.lang.String
				obj = String.valueOf(valor);
			}
			vDatos.add(i, obj);
			i++;
		}
		return vDatos;
	}


	

}
