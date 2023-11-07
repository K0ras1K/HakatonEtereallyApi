package ru.k0ras1k.ethereally.console.commands

import ru.k0ras1k.ethereally.console.ICommand

@Deprecated("Old command")
class SetOnlineCommand : ICommand {
    override fun getName(): String {
        return "setonline"
    }

    override fun getCommandUsage(): String {
        return "/setonline <server> <online>"
    }
    override fun processCommand(args: MutableList<String>) {

    }
}
