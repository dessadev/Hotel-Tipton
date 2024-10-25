lateinit var nome: String
val quartos = MutableList(20) { "Livre" }

fun main() {
    inicio()
}

fun inicio() {
    var senha: String

    println("Insira seu nome:")
    nome = readln()

    do {
        println("Insira a senha:")
        senha = readln()

        if (senha != "2678") {
            println("Senha incorreta. Tente novamente.")
        }
    } while (senha != "2678")

    println("Bem-vindo ao Hotel Tipton, $nome!")
    menu()
}

fun menu() {
    println("Escolha uma opção:")
    println("1. Cadastrar Quartos")
    println("2. Cadastrar Hóspedes")
    println("3. Abastecimento de Automóveis")
    println("4. Agendar Eventos")
    println("5. Manutenção de Ar Condicionado")
    println("6. Sair do Hotel")

    val escolha = readln().toIntOrNull()
    when (escolha) {
        1 -> cadastrarQuartos()
        2 -> cadastrarHospedes()
        3 -> abastecimentoDeAutomoveis()
        4 -> agendarEventos()
        5 -> manutencaoArCondicionado()
        6 -> sairDoHotel()
        else -> erro()
    }
}

fun cadastrarQuartos() {
    var diaria: Double
    var dias: Int

    do {
        println("Qual o valor padrão da diária?")
        diaria = readln().toDoubleOrNull() ?: -1.0

        if (diaria <= 0) {
            println("Valor inválido, $nome")
        }
    } while (diaria <= 0)

    do {
        println("Quantas diárias serão necessárias?")
        dias = readln().toIntOrNull() ?: -1

        if (dias <= 0) {
            println("Valor inválido, $nome")
        }
    } while (dias <= 0)

    val total = diaria * dias
    println("O valor de $dias dias de hospedagem é de R$$total")

    println("Qual o nome do hóspede?")
    val hospede = readln()

    var quarto: Int
    do {
        println("Qual o quarto para reserva? (1 - 20)?")
        quarto = readln().toIntOrNull() ?: -1
        if (quarto < 1 || quarto > 20) {
            println("Número do quarto inválido.")
        } else if (quartos[quarto - 1] != "Livre") {
            println("Quarto já está ocupado. Escolha outro.")
        }
    } while (quarto < 1 || quarto > 20 || quartos[quarto - 1] != "Livre")

    println("$nome, você confirma a hospedagem para $hospede por $dias dias para o quarto $quarto por R$$total? S/N")
    val confirmacao = readln().uppercase()
    if (confirmacao == "S") {
        quartos[quarto - 1] = hospede
        println("$nome, reserva efetuada para $hospede.")
    } else {
        println("Reserva não confirmada.")
    }

    menu()
}

fun cadastrarHospedes() {
    var diaria: Double

    do {
        println("Qual o valor padrão da diária?")
        diaria = readln().toDoubleOrNull() ?: -1.0

        if (diaria <= 0) {
            println("Valor inválido, $nome")
        }
    } while (diaria <= 0)

    var gratuidade = 0
    var meia = 0
    var valorTotal = 0.0
    val hospedes = mutableListOf<String>()

    while (true) {
        println("Selecione uma opção: 1. Cadastrar - 2. Pesquisar - 3. Sair")
        val escolha = readln().toIntOrNull() ?: -1

        when (escolha) {
            1 -> {
                println("Qual o nome do Hóspede?")
                val hospede = readln()

                if (hospede.uppercase() == "PARE") break

                println("Qual a idade do Hóspede?")
                val idade = readln().toIntOrNull() ?: -1

                if (idade < 6) {
                    println("Hóspede $hospede foi cadastrada(o) com sucesso! $hospede possui gratuidade.")
                    gratuidade++
                } else if (idade > 60) {
                    println("Hóspede $hospede foi cadastrada(o) com sucesso! $hospede paga meia.")
                    meia++
                    valorTotal += diaria / 2
                } else {
                    println("Hóspede $hospede foi cadastrada(o) com sucesso!")
                    valorTotal += diaria
                }

                hospedes.add(hospede)

                if (hospedes.size == 15) {
                    println("Máximo de cadastros atingido.")
                    break
                }
            }
            2 -> {
                println("Qual o nome do Hóspede?")
                val hospede = readln()
                if (hospedes.contains(hospede)) {
                    println("Hóspede $hospede foi encontrada(o)!")
                } else {
                    println("Hóspede $hospede não foi encontrada(o)!")
                }
            }
            3 -> break
            else -> println("Opção inválida. Tente novamente.")
        }
    }

    println("$nome, o valor total das hospedagens é: R$$valorTotal; $gratuidade gratuidade(s); $meia meia(s)")
    menu()
}

fun abastecimentoDeAutomoveis() {
    println("Qual o valor do álcool no posto Wayne Oil?")
    val precoAlcoolWayne = readln().toDoubleOrNull() ?: 0.0

    println("Qual o valor da gasolina no posto Wayne Oil?")
    val precoGasolinaWayne = readln().toDoubleOrNull() ?: 0.0

    println("Qual o valor do álcool no posto Stark Petrol?")
    val precoAlcoolStark = readln().toDoubleOrNull() ?: 0.0

    println("Qual o valor da gasolina no posto Stark Petrol?")
    val precoGasolinaStark = readln().toDoubleOrNull() ?: 0.0

    val tanqueCapacidade = 42.0

    val custoAlcoolWayne = precoAlcoolWayne * tanqueCapacidade
    val custoGasolinaWayne = precoGasolinaWayne * tanqueCapacidade
    val custoAlcoolStark = precoAlcoolStark * tanqueCapacidade
    val custoGasolinaStark = precoGasolinaStark * tanqueCapacidade

    val menorCustoWayne = if (custoAlcoolWayne < custoGasolinaWayne) "álcool" else "gasolina"
    val menorCustoStark = if (custoAlcoolStark < custoGasolinaStark) "álcool" else "gasolina"

    if (precoAlcoolWayne * 0.7 < precoGasolinaWayne) {
        println("$nome, é mais barato abastecer com álcool no posto Wayne Oil.")
    } else {
        println("$nome, é mais barato abastecer com gasolina no posto Wayne Oil.")
    }

    if (precoAlcoolStark * 0.7 < precoGasolinaStark) {
        println("$nome, é mais barato abastecer com álcool no posto Stark Petrol.")
    } else {
        println("$nome, é mais barato abastecer com gasolina no posto Stark Petrol.")
    }

    menu()
}

fun agendarEventos() {
    println("Qual o número de convidados para o seu evento?")
    val convidados = readln().toIntOrNull() ?: -1

    if (convidados > 350 || convidados < 0) {
        println("Número de convidados inválido.")
    } else {
        val auditório = if (convidados <= 150) "Auditório Laranja" else "Auditório Colorado"
        val adicionais = if (convidados <= 150 && convidados > 70) " (inclua mais ${convidados - 150} cadeiras)" else ""
        println("Use o $auditório$adicionais.")
        println("Agora vamos ver a agenda do evento.")

        println("Qual o dia do evento?")
        val dia = readln()
        println("Qual é a hora do evento?")
        val hora = readln().toIntOrNull() ?: -1
        println("Qual o nome da empresa?")
        val empresa = readln()

        if ((dia in listOf("segunda", "terca", "quarta", "quinta", "sexta") && hora in 7..23) ||
            (dia in listOf("sabado", "domingo") && hora in 7..15)) {
            println("Auditório reservado para $empresa. $dia às ${hora}hs.")
        } else {
            println("Auditório indisponível.")
            return
        }

        println("Qual a duração do evento em horas?")
        val duracao = readln().toIntOrNull() ?: -1
        val garçons = (convidados / 12) + (duracao / 2)
        val custoGarçons = garçons * 10.50
        println("São necessários $garçons garçons.")
        println("Custo: R$$custoGarçons")
    }

    menu()
}

fun manutencaoArCondicionado() {
    println("Qual o número do quarto para manutenção do ar condicionado?")
    val quartoManutencao = readln().toIntOrNull() ?: -1

    if (quartoManutencao < 1 || quartoManutencao > 20) {
        println("Número do quarto inválido.")
    } else {
        println("Manutenção do ar condicionado para o quarto $quartoManutencao agendada.")
    }

    menu()
}

fun sairDoHotel() {
    println("Saindo do hotel. Até logo, $nome!")
}

fun erro() {
    println("Opção inválida. Tente novamente.")
    menu()
}
