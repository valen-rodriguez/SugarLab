package com.zn.challengebookcompose.challengebook2.pokemon.models




enum class Efectividad {
    MUY_EFICAZ, // x2
    NORMAL,      // x1
    POCO_EFICAZ, // x0.5
    NULO         // x0
}

// Enum para el tipo de Pokémon
enum class Tipo {
    NORMAL {
        override fun getEfectividadContra(otroTipo: Tipo): Efectividad {
            return when (otroTipo) {
                ROCK, STEEL -> Efectividad.POCO_EFICAZ
                GHOST -> Efectividad.NULO
                else -> Efectividad.NORMAL
            }
        }
    },
    FIRE {
        override fun getEfectividadContra(otroTipo: Tipo): Efectividad {
            return when (otroTipo) {
                GRASS, ICE, BUG, STEEL -> Efectividad.MUY_EFICAZ
                FIRE, WATER, ROCK, DRAGON -> Efectividad.POCO_EFICAZ
                else -> Efectividad.NORMAL
            }
        }
    },
    WATER {
        override fun getEfectividadContra(otroTipo: Tipo): Efectividad {
            return when (otroTipo) {
                FIRE, GROUND, ROCK -> Efectividad.MUY_EFICAZ
                WATER, GRASS, DRAGON -> Efectividad.POCO_EFICAZ
                else -> Efectividad.NORMAL
            }
        }
    },
    GRASS {
        override fun getEfectividadContra(otroTipo: Tipo): Efectividad {
            return when (otroTipo) {
                WATER, GROUND, ROCK -> Efectividad.MUY_EFICAZ
                FIRE, GRASS, POISON, FLYING, BUG, DRAGON, STEEL -> Efectividad.POCO_EFICAZ
                else -> Efectividad.NORMAL
            }
        }
    },
    ELECTRIC {
        override fun getEfectividadContra(otroTipo: Tipo): Efectividad {
            return when (otroTipo) {
                WATER, FLYING -> Efectividad.MUY_EFICAZ
                GRASS, ELECTRIC, DRAGON -> Efectividad.POCO_EFICAZ
                GROUND -> Efectividad.NULO
                else -> Efectividad.NORMAL
            }
        }
    },
    ICE {
        override fun getEfectividadContra(otroTipo: Tipo): Efectividad {
            return when (otroTipo) {
                GRASS, GROUND, FLYING, DRAGON -> Efectividad.MUY_EFICAZ
                FIRE, WATER, ICE, STEEL -> Efectividad.POCO_EFICAZ
                else -> Efectividad.NORMAL
            }
        }
    },
    FIGHTING {
        override fun getEfectividadContra(otroTipo: Tipo): Efectividad {
            return when (otroTipo) {
                NORMAL, ICE, ROCK, DARK, STEEL -> Efectividad.MUY_EFICAZ
                POISON, FLYING, PSYCHIC, BUG, FAIRY -> Efectividad.POCO_EFICAZ
                GHOST -> Efectividad.NULO
                else -> Efectividad.NORMAL
            }
        }
    },
    POISON {
        override fun getEfectividadContra(otroTipo: Tipo): Efectividad {
            return when (otroTipo) {
                GRASS, FAIRY -> Efectividad.MUY_EFICAZ
                POISON, GROUND, ROCK, GHOST -> Efectividad.POCO_EFICAZ
                STEEL -> Efectividad.NULO
                else -> Efectividad.NORMAL
            }
        }
    },
    GROUND {
        override fun getEfectividadContra(otroTipo: Tipo): Efectividad {
            return when (otroTipo) {
                FIRE, ELECTRIC, POISON, ROCK, STEEL -> Efectividad.MUY_EFICAZ
                GRASS, BUG -> Efectividad.POCO_EFICAZ
                FLYING -> Efectividad.NULO
                else -> Efectividad.NORMAL
            }
        }
    },
    FLYING {
        override fun getEfectividadContra(otroTipo: Tipo): Efectividad {
            return when (otroTipo) {
                GRASS, FIGHTING, BUG -> Efectividad.MUY_EFICAZ
                ELECTRIC, ROCK, STEEL -> Efectividad.POCO_EFICAZ
                else -> Efectividad.NORMAL
            }
        }
    },
    PSYCHIC {
        override fun getEfectividadContra(otroTipo: Tipo): Efectividad {
            return when (otroTipo) {
                FIGHTING, POISON -> Efectividad.MUY_EFICAZ
                PSYCHIC, STEEL -> Efectividad.POCO_EFICAZ
                DARK -> Efectividad.NULO
                else -> Efectividad.NORMAL
            }
        }
    },
    BUG {
        override fun getEfectividadContra(otroTipo: Tipo): Efectividad {
            return when (otroTipo) {
                GRASS, PSYCHIC, DARK -> Efectividad.MUY_EFICAZ
                FIRE, FIGHTING, POISON, FLYING, GHOST, STEEL, FAIRY -> Efectividad.POCO_EFICAZ
                else -> Efectividad.NORMAL
            }
        }
    },
    ROCK {
        override fun getEfectividadContra(otroTipo: Tipo): Efectividad {
            return when (otroTipo) {
                FIRE, ICE, FLYING, BUG -> Efectividad.MUY_EFICAZ
                FIGHTING, GROUND, STEEL -> Efectividad.POCO_EFICAZ
                else -> Efectividad.NORMAL
            }
        }
    },
    GHOST {
        override fun getEfectividadContra(otroTipo: Tipo): Efectividad {
            return when (otroTipo) {
                PSYCHIC, GHOST -> Efectividad.MUY_EFICAZ
                DARK -> Efectividad.POCO_EFICAZ
                NORMAL -> Efectividad.NULO
                else -> Efectividad.NORMAL
            }
        }
    },
    DRAGON {
        override fun getEfectividadContra(otroTipo: Tipo): Efectividad {
            return when (otroTipo) {
                DRAGON -> Efectividad.MUY_EFICAZ
                STEEL -> Efectividad.POCO_EFICAZ
                FAIRY -> Efectividad.NULO
                else -> Efectividad.NORMAL
            }
        }
    },
    STEEL {
        override fun getEfectividadContra(otroTipo: Tipo): Efectividad {
            return when (otroTipo) {
                ICE, ROCK, FAIRY -> Efectividad.MUY_EFICAZ
                FIRE, WATER, ELECTRIC, STEEL -> Efectividad.POCO_EFICAZ
                else -> Efectividad.NORMAL
            }
        }
    },
    DARK {
        override fun getEfectividadContra(otroTipo: Tipo): Efectividad {
            return when (otroTipo) {
                PSYCHIC, GHOST -> Efectividad.MUY_EFICAZ
                FIGHTING, DARK, FAIRY -> Efectividad.POCO_EFICAZ
                else -> Efectividad.NORMAL
            }
        }
    },
    FAIRY {
        override fun getEfectividadContra(otroTipo: Tipo): Efectividad {
            return when (otroTipo) {
                FIGHTING, DRAGON, DARK -> Efectividad.MUY_EFICAZ
                FIRE, POISON, STEEL -> Efectividad.POCO_EFICAZ
                else -> Efectividad.NORMAL
            }
        }
    },
    UNKNOWN {
        override fun getEfectividadContra(otroTipo: Tipo): Efectividad {
            return Efectividad.NORMAL
        }
    }; // <-- El punto y coma es necesario al final del enum

    // Método abstracto que debe ser implementado por cada tipo
    abstract fun getEfectividadContra(otroTipo: Tipo): Efectividad
}