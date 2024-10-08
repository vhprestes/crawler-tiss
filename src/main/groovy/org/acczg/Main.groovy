package org.acczg


import org.acczg.Service.EmailSender
import org.acczg.Tasks.Task1
import org.acczg.Tasks.Task2
import org.acczg.Tasks.Task3


static main(String[] args) {

    String URL = "https://www.gov.br/ans/pt-br"


    println "Bem vindo ao sistema de automação de tarefas da ANS \n" +
            "Escolha uma das opções abaixo: \n" +
            " 1 - Baixar arquivo padrão TISS \n " +
            "2 - Baixar dados de tabela \n " +
            "3 - Baixar arquivo de erros no envio \n" +
            " 4 - Menu de emails (CRUD) \n"


    def option = System.in.newReader().readLine()
    switch (option) {
        case "1":
            Task1.run(URL)
            break
        case "2":
            Task2.run(URL)
            break
        case "3":
            Task3.run(URL)
            break
        case "4":
            EmailSender.run()
            break
        default:
            println "Opção inválida"
    }


}