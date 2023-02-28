package org.example;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.example.lib.JsonTransformer;
import org.example.model.user.TransactionTable;
import org.example.model.user.UserTable;

import java.util.*;

import static org.example.lib.Helper.*;
import static spark.Spark.get;
import static spark.Spark.port;

public class Main {
    public static void main(String[] args) {
        port(8080);
        Unirest.config()
                .connectTimeout(1000)
                .setDefaultHeader("accept", "application/json");

        get("/", "application/json", (req, res) -> "Belajar menggunakan Java Spark menggunakan IntelJ");

        get("/user", "application/json",(request, response) -> {

            UserTable userAdmin = new UserTable();
            userAdmin.setEmail("sadmin@gmail.com");
            userAdmin.setFullname("Sahtia Murti");
            userAdmin.setPassword("namasaya");

            UserTable userFinance = new UserTable();
            userFinance.setEmail("finance@gmail.com");
            userFinance.setFullname("Finance");
            userFinance.setPassword("finance123");

            List<UserTable> users = new ArrayList<>();
            users.add(userAdmin);
            users.add(userFinance);

            return users;
        }, new JsonTransformer());

        get("/inquiry", "application/json",(request, response) -> {
            System.out.println("Masuk request => " + new Date());

            HttpResponse<JsonNode> unirest;
            try {
                unirest = Unirest.get("https://jsonplaceholder.typicode.com/posts")
                        .asJson();
            } catch (Exception e){
                e.printStackTrace();
                System.out.println("Selesai request => " + new Date());
                return "error timeout => " + e.getMessage();
            }

            System.out.println("Response Status => " + unirest.getStatus());
            System.out.println("Response Status Txt => " + unirest.getStatusText());
            System.out.println("Response Body => " + unirest.getBody());

            System.out.println("Selesai request => " + new Date());
            return unirest.getBody();
        });

        get("/login", (request, response) -> {
            List<TransactionTable> transactionTables = new ArrayList<>();

            TransactionTable data1 = new TransactionTable();
            data1.setProductName("PLN Postpaid");
            data1.setNumberTrxSuccess("100");
            data1.setNumberTrxFailed("4");

            TransactionTable data2 = new TransactionTable();
            data2.setProductName("PLN Prepiad");
            data2.setNumberTrxSuccess("250");
            data2.setNumberTrxFailed("6");

            TransactionTable data3 = new TransactionTable();
            data3.setProductName("BPJS Kesehatan");
            data3.setNumberTrxSuccess("40");
            data3.setNumberTrxFailed("1");

            transactionTables.add(data1);
            transactionTables.add(data2);
            transactionTables.add(data3);

            Map<String, Object> model = new HashMap<>();
            model.put("name", "Sahtia");
            model.put("transactions", transactionTables);
            return render(model, "report.html");
        });
    }
}