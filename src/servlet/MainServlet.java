package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import listener.Data;
import utils.RandomUtil;

public class MainServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 先获取操作类型
		String action = request.getParameter("action");
		if ("randGenerate".equals(action)) {
			randGenerate(request, response);
		}
	}

	public void randGenerate(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String plateNumber[] = new String[5];
		// 接收前端数据
		String province = request.getParameter("province");
		String area = request.getParameter("area");
		String code1 = request.getParameter("code1");
		String code2 = request.getParameter("code2");
		String code3 = request.getParameter("code3");
		String code4 = request.getParameter("code4");
		String code5 = request.getParameter("code5");

		Data data = (Data) request.getSession().getServletContext()
				.getAttribute("data");

		int n = 7;

		if (province != null && province.equals("*")) {
			for (int i = 0; i < plateNumber.length; i++) {
				province = RandomUtil.getRandomValueFromMap(data.getDataMap()
						.get("province"));
				plateNumber[i] = province;
			}
		} else {
			for (int i = 0; i < plateNumber.length; i++) {
				plateNumber[i] = province;
			}
			n--;
			request.setAttribute("province1", province);
		}
		if (area != null && (area.equals("*") || area.equals("-1"))) {
			for (int i = 0; i < plateNumber.length; i++) {
				area = RandomUtil.getRandomKeyFromMap(data.getDataMap().get(
						province));
				plateNumber[i] += area;
			}
		} else {
			for (int i = 0; i < plateNumber.length; i++) {
				plateNumber[i] += area;
			}
			n--;
			request.setAttribute("area1", area);
		}
		if (code1 != null && code1.equals("*")) {
			for (int i = 0; i < plateNumber.length; i++) {
				code1 = RandomUtil.getRandomElement(data.getCodeList());
				plateNumber[i] += code1;
			}
		} else {
			for (int i = 0; i < plateNumber.length; i++) {
				plateNumber[i] += code1;
			}
			n--;
			request.setAttribute("code1", code1);

		}
		if (code2 != null && code2.equals("*")) {
			for (int i = 0; i < plateNumber.length; i++) {
				code2 = RandomUtil.getRandomElement(data.getCodeList());
				plateNumber[i] += code2;
			}
		} else {
			for (int i = 0; i < plateNumber.length; i++) {
				plateNumber[i] += code2;
			}
			n--;
			request.setAttribute("code2", code2);

		}
		if (code3 != null && code3.equals("*")) {
			for (int i = 0; i < plateNumber.length; i++) {
				code3 = RandomUtil.getRandomElement(data.getCodeList());
				plateNumber[i] += code3;
			}
		} else {
			for (int i = 0; i < plateNumber.length; i++) {
				plateNumber[i] += code3;
			}
			n--;
			request.setAttribute("code3", code3);
		}
		if (code4 != null && code4.equals("*")) {
			for (int i = 0; i < plateNumber.length; i++) {
				code4 = RandomUtil.getRandomElement(data.getCodeList());
				plateNumber[i] += code4;
			}
		} else {
			for (int i = 0; i < plateNumber.length; i++) {
				plateNumber[i] += code4;
			}
			n--;
			request.setAttribute("code4", code4);
		}
		if (code5 != null && code5.equals("*")) {
			for (int i = 0; i < plateNumber.length; i++) {
				code5 = RandomUtil.getRandomElement(data.getCodeList());
				plateNumber[i] += code5;
			}
		} else {
			for (int i = 0; i < plateNumber.length; i++) {
				plateNumber[i] += code5;
			}
			n--;
			request.setAttribute("code5", code5);
		}
		if (n == 7) {
			request.setAttribute("type", "随机选号");
		} else if (n == 0) {
			for (int i = 1; i < plateNumber.length; i++) {
				plateNumber[i] = null;
			}
			request.setAttribute("type", "指定选号");
		} else {
			request.setAttribute("type", "限制选号");
		}
		request.setAttribute("plateNumber", plateNumber);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
