package org.acczg.Tasks

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import static groovyx.net.http.HttpBuilder.configure
import groovyx.net.http.optional.Download


class Task1 {

    static def run(baseUrl) {

            try {
                Document doc = Jsoup.connect(baseUrl).get()

                String firstLink = doc.select("ul.list-navigation:nth-child(2) > li:nth-child(1) > ul:nth-child(2) > li:nth-child(6) > a:nth-child(1)").attr("href")
                doc = Jsoup.connect(firstLink).get()

                String secondLink = doc.select("div.card:nth-child(1) > a:nth-child(1)").attr("href")
                doc = Jsoup.connect(secondLink).get()

                String thirdLink = doc.select("p.callout:nth-child(4) > a:nth-child(1)").attr("href")
                doc = Jsoup.connect(thirdLink).get()


                String downloadLink = doc.select(".table > tbody:nth-child(2) > tr:nth-child(5) > td:nth-child(3) > a:nth-child(1)").attr("href")

//                def fileResponse = Jsoup.connect(downloadLink).ignoreContentType(true).execute()
//                new File("../../downloads/arquivo_padrao_tiss.zip").withOutputStream { it << fileResponse.bodyStream() }
//                print("Arquivo baixado com sucesso")

                File getFile = new File("../../downloads/arquivo_padrao_tiss.zip")
                configure {
                    request.uri = downloadLink
                }.get {
                    Download.toFile(delegate, getFile)
                    println "Arquivo baixado com sucesso"
                }

            }
            catch (Exception e) {
                println "Erro ao baixar arquivo padrão TISS" + e
            }
        }
    }
