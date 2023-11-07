package ru.k0ras1k.ethereally.database.hard

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

object Hard : Table("hwids") {

    private val id = Hard.integer("id")
    private val banned = Hard.bool("banned")
    private val hwdiskid = Hard.varchar("hwDiskId", 255)
    private val baseboardserialnumber = Hard.varchar("baseboardSerialNumber", 255)
    private val graphiccard = Hard.varchar("graphicCard", 255)
    private val bitness = Hard.integer("bitness")
    private val totalmemory = Hard.integer("totalMemory")
    private val logicalprocessors = Hard.integer("logicalProcessors")
    private val physicalProcessors = Hard.integer("physicalProcessors")
    private val processormaxfreq = Hard.integer("processorMaxFreq")

    fun fetchHard(banid: Int): HardDTO? {
        return try {
            transaction {
                val groupModel = Hard.select { Hard.id.eq(banid) }.single()
                if (groupModel[banned] == true) {
                    HardDTO(
                        id = banid,
                        banned = 1,
                        hwdiskid = groupModel[hwdiskid],
                        baseboardserialnumber = groupModel[baseboardserialnumber],
                        graphiccard = groupModel[graphiccard],
                        bitness = groupModel[bitness],
                        totalmemory = groupModel[totalmemory],
                        logicalprocessors = groupModel[logicalprocessors],
                        physicalProcessors = groupModel[physicalProcessors],
                        processormaxfreq = groupModel[processormaxfreq],
                    )
                } else {
                    HardDTO(
                        id = banid,
                        banned = 0,
                        hwdiskid = groupModel[hwdiskid],
                        baseboardserialnumber = groupModel[baseboardserialnumber],
                        graphiccard = groupModel[graphiccard],
                        bitness = groupModel[bitness],
                        totalmemory = groupModel[totalmemory],
                        logicalprocessors = groupModel[logicalprocessors],
                        physicalProcessors = groupModel[physicalProcessors],
                        processormaxfreq = groupModel[processormaxfreq],
                    )
                }
            }
        } catch (e: Exception) {
            println(e.message)
            null
        }
    }

    fun hardBan(banidid: Int) {
        transaction {
            Hard.update({ Hard.id.eq(banidid) }) {
                it[banned] = true
            }
        }
    }

    fun unHardBan(banidid: Int) {
        transaction {
            Hard.update({ Hard.id.eq(banidid) }) {
                it[banned] = false
            }
        }
    }
}
