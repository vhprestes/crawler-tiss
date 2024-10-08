package org.acczg.Tasks

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

class Task2 {

    static def run(baseUrl) {
        try {
            Document doc = Jsoup.connect(baseUrl).get()

            String firstLink = doc.select("ul.list-navigation:nth-child(2) > li:nth-child(1) > ul:nth-child(2) > li:nth-child(6) > a:nth-child(1)").attr("href")
            doc = Jsoup.connect(firstLink).get()

            String secondLink = doc.select("div.card:nth-child(1) > a:nth-child(1)").attr("href")
            doc = Jsoup.connect(secondLink).get()

            String thirdLink = doc.select("p.callout:nth-child(6) > a:nth-child(1)").attr("href")
            doc = Jsoup.connect(thirdLink).get()

            String table = doc.select("table").toString()

            Document tableDoc = Jsoup.parse(table)
            Elements rows = tableDoc.select("tr")

            // Iterate over rows to find what we want
            List<String> data = []
            rows.each { row ->
                Elements cells = row.select("td")
                if (cells.size() > 0) {
                    String inicioVigencia = cells.get(2).text()
                    if (lastForDigits(inicioVigencia) >= 2016) {
                        String competencia = cells.get(0).text()
                        String publicacao = cells.get(1).text()
                        data.add("Competência: $competencia, Publicação: $publicacao, Início de Vigência: $inicioVigencia")
                    }
                }
            }
            File csvFile = new File("../../downloads/", "data.csv")
            csvFile.withWriter { writer ->
                data.each { line ->
                    writer.writeLine(line)
                }
            }
            println "CSV gerado com sucesso!"

        } catch (Exception e) {
            println "Erro ao ler a tabela e gerar o CSV: " + e
        }
    }

    static int lastForDigits(String input) {
        String lastDigits = input[-4..-1]
        return Integer.parseInt(lastDigits)
    }
}