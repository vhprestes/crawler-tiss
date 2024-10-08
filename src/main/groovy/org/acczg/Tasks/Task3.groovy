package org.acczg.Tasks

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class Task3 {

    static def run(baseURL) {
        Document doc = Jsoup.connect(baseURL).get()

        String firstLink = doc.select("ul.list-navigation:nth-child(2) > li:nth-child(1) > ul:nth-child(2) > li:nth-child(6) > a:nth-child(1)").attr("href")
        println("First link: $firstLink")

        doc = Jsoup.connect(firstLink).get()

        String secondLink = doc.select("div.card:nth-child(1) > a:nth-child(1)").attr("href")
        println("Second link: $secondLink")
        doc = Jsoup.connect(secondLink).get()

        String thirdLink = doc.select("p.callout:nth-child(8) > a:nth-child(1)").attr("href")
        println("Third link: $thirdLink")
        doc = Jsoup.connect(thirdLink).get()

        String downloadLink = doc.select("p.callout:nth-child(2) > a:nth-child(1)").attr("href")
        println("Download link: $downloadLink")
        def fileResponse = Jsoup.connect(downloadLink).ignoreContentType(true).execute()
        new File("../../downloads/erros_no_envio_ANS.xlsx").withOutputStream { it << fileResponse.bodyStream() }


    }
}
