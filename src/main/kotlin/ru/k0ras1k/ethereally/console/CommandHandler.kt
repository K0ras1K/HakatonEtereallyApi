package ru.k0ras1k.ethereally.console

import ru.k0ras1k.ethereally.utils.Logger


object CommandHandler {

    fun handle(command: String) {
        // Getting commands args - strings array after command name
        var command_args: List<String> = command.split(" ")
        val command_name = command_args[0]
        // Dropping first arg because it's the name of command
        command_args = command_args.drop(1)
        // Checking valid command
        when {
            command_name in CommandRegistry.commands.keys -> CommandRegistry.commands[command_name]!!.processCommand(
                command_args.toMutableList(),
            )

            else -> Logger.log("Такой команды не существует!")
        }
    }
}
