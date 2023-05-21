package com.dbViewer.dbViewer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

@SpringBootApplication
@RestController
public class DbViewerApplication {

	public static void main(String[] args) {
//		System.out.println(DatabaseConnector.getDatabaseInfo());
		SpringApplication.run(DbViewerApplication.class, args);
	}

	@GetMapping("/")
	public String test() {
		StringBuilder stringBuilder = new StringBuilder("<h4>Currently using " + DatabaseConnector.getDatabaseName() + " database.</h4>\n");
		HashSet<String> hashSet = DatabaseConnector.getDatabaseInfo();

		stringBuilder.append("<form id=\"myForm\">");
		stringBuilder.append("<select id=\"tableNameSelect\">");
		hashSet.forEach(option -> stringBuilder.append("<option>").append(option).append("</option>"));
		stringBuilder.append("</select>");
		stringBuilder.append("</form>");

		stringBuilder.append("<button type=\"button\" onclick=\"gotoTable()\">Pokaż zawartość tabeli</button>");

		// stringBuilder.append("<div id=\"displayDiv\"></div>");

		stringBuilder.append("<script>");
		stringBuilder.append("function gotoTable() {");
		stringBuilder.append("	var tableName = document.getElementById('tableNameSelect').value;");
		stringBuilder.append("	window.location.href = '/tables/' + tableName;");
		stringBuilder.append("}");
		stringBuilder.append("</script>");

		return stringBuilder.toString();
	}

	@GetMapping("/test")
	public String test2() {
		return "123";
	}

	@GetMapping("/tables/{tableName}")
	public String getTableContent(@PathVariable String tableName) {
		StringBuilder stringBuilder = new StringBuilder("<html>");
		stringBuilder.append(DatabaseConnector.getTableContent(tableName));

		stringBuilder.append("<br><br>");
		stringBuilder.append("<button type=\"button\" onclick=\"goToRoot()\">goToRoot</button>");

		stringBuilder.append("<script>");
		stringBuilder.append("function goToRoot() {");
		stringBuilder.append("	window.location.href = '/';");
		stringBuilder.append("}");
		stringBuilder.append("</script>");
		stringBuilder.append("</html>");

		return stringBuilder.toString();
	}

}
