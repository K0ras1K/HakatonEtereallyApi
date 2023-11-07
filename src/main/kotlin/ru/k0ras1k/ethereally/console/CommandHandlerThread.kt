package ru.k0ras1k.ethereally.console

import ru.k0ras1k.ethereally.utils.Logger


object CommandHandlerThread {

    fun initialize() {
        Logger.log("Started command handler initialization in separate thread")
        CommandRegistry.initialize()
        // Creating new thread for handling commands
        Thread {
            while (true) {
                // Reading console input
                val input = readLine()
                if (input != null) {
                    when {
                        // Starting handling without '/'
                        input[0] == '/' -> CommandHandler.handle(input.toString().drop(1))
                        else -> Logger.log("Команда должна начинаться с /")
                    }
                } else {
                    Logger.error("Такой команды не существует!")
                }
            }
        }.start()
    }
}
