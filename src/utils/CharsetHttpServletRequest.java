package utils;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class CharsetHttpServletRequest extends HttpServletRequestWrapper {

	public CharsetHttpServletRequest(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {
		String value = super.getParameter(name);
		value = convertToUtf_8(value);

		return value;
	}

	private String convertToUtf_8(String oldValue) {
		if (oldValue != null && oldValue.length() > 0) {
			try {
				oldValue = new String(oldValue.getBytes("ISO-8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}
		return oldValue;
	}

	private String[] convertToUtf_8(String[] values) {

		if (values == null || values.length == 0) {
			return values;
		}
		String[] newValues = new String[values.length];
		for (int i = 0; i < values.length; i++) {
			newValues[i] = convertToUtf_8(values[i]);
		}

		return newValues;
	}

	// Map<String,String[]>
	@SuppressWarnings("unchecked")
	@Override
	public Map getParameterMap() {
		Map<String, String[]> map = super.getParameterMap();
		Map<String, String[]> newMap = new HashMap<String, String[]>();
		for (Entry<String, String[]> entry : map.entrySet()) {
			newMap.put(convertToUtf_8(entry.getKey()), convertToUtf_8(entry
					.getValue()));
		}
		return newMap;
	}

	@Override
	public String[] getParameterValues(String name) {
		return convertToUtf_8(super.getParameterValues(name));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Enumeration getParameterNames() {
		final Enumeration oldEnum = super.getParameterNames();
		Enumeration<String> newEnum = new Enumeration<String>() {

			public String nextElement() {
				return convertToUtf_8((String) oldEnum.nextElement());
			}

			public boolean hasMoreElements() {
				return oldEnum.hasMoreElements();
			}
		};

		return newEnum;
	}
}
