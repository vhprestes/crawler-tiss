package org.acczg.Service

class EmailSender {
    static def run() {
        boolean menuON = true
        while (menuON) {
            println "Selecione a opção desejada \n " +
                    "1 - Enviar os arquivos baixados para um email \n " +
                    "2 - Cadastrar novo email \n " + "3 - Listar emails cadastrados \n " +
                    "4 - Remover email cadastrado \n " +
                    "5 - Sair"

            def option = System.in.newReader().readLine()

            switch (option) {
                case "1":
                    sendEmail()
                    menuON = false
                    break
                case "2":
                    addEmail()
                    break
                case "3":
                    println "Emails cadastrados:"
                    listarEmails()
                    break
                case "4":
                    removeEmail()
                    break
                case "5":
                    menuON = false
                    break
                default:
                    println "Opção inválida"
            }
        }
    }

    static void listarEmails() {

        def console = getEmailsCadastrados()
        int tempIndex = 1
        console.each { email ->
            println "$tempIndex - $email"
            tempIndex++
        }
    }

    static List getEmailsCadastrados() {
        List tempList = []
        File emailFile = new File("./utils/emails.csv")
        if (!emailFile.exists()) {
            emailFile.createNewFile()
            println "Nenhum email cadastrado! Cadastre um novo email no menu abaixo:"
        }
        def emails = emailFile.readLines()
        emails.each { email ->
            tempList.add(email)
        }
        return tempList
    }

    def static void sendEmail() {
        def emails = getEmailsCadastrados()
        println "Selecione o código do email que deseja enviar os arquivos:"
        def emailIndex = System.in.newReader().readLine()
        def email = emails.get(emailIndex.toInteger() - 1)
        println "Enviando email para $email"

        // Implementar envio de email


        println "Email enviado com sucesso! Encerrando a aplicação"

    }

    def static void addEmail() {
        println "Digite o email que deseja adicionar: "
        def email = System.in.newReader().readLine()
        def oldMails = getEmailsCadastrados()
        new File("./utils/emails.csv").withWriter { writer ->
            oldMails.each { a ->
                writer.writeLine(a as String)
            }
            writer.writeLine(email)
        }
        println "Email cadastrado com sucesso!"
    }

    def static void removeEmail() {
        def emails = getEmailsCadastrados()
        println "Selecione o código do email que deseja remover:"
        listarEmails()
        def emailIndex = System.in.newReader().readLine()
        def email = emails.get(emailIndex.toInteger() - 1)
        def newEmails = emails - email
        new File("./utils/emails.csv").withWriter { writer ->
            newEmails.each { a ->
                writer.writeLine(a as String)
            }
        }
        println "Email removido com sucesso!"
    }

}
