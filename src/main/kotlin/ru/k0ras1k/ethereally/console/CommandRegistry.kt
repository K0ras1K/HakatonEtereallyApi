package ru.k0ras1k.ethereally.console

import ru.k0ras1k.ethereally.console.commands.SetOnlineCommand
import ru.k0ras1k.ethereally.utils.Logger

// Commands registration
object CommandRegistry {

    // Array with all commands
    var commands: MutableMap<String, ICommand> = mutableMapOf()

    private fun register(command: ICommand) {
        commands[command.getName()] = command
        Logger.log("Command `${command.getName()}` has registered")
    }

    // Static function to register commands
    fun initialize() {
        Logger.log("Starting command registration")
        register(SetOnlineCommand())
        Logger.log("All commands successfully registered")
    }
}
