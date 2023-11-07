package ru.k0ras1k.ethereally.console

// Interface for simple command
interface ICommand {

    fun getName(): String

    fun getCommandUsage(): String

    fun processCommand(args: MutableList<String>)
}
