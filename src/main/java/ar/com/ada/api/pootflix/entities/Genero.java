package ar.com.ada.api.pootflix.entities;

public class Genero {
    private Integer nombre;

    public enum TipoGeneroEnum {
        ROMANCE(1), ACCION(2), FANTASIA(3), SUSPENSO(4), DRAMA(5);

        private final Integer value;

        // NOTE: Enum constructor tiene que estar en privado
        private TipoGeneroEnum(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public static TipoGeneroEnum parse(Integer id) {
            TipoGeneroEnum status = null; // Default
            for (TipoGeneroEnum item : TipoGeneroEnum.values()) {
                if (item.getValue().equals(id)) {
                    status = item;
                    break;
                }
            }
            return status;
        }
    }

    /**
     * @return the estilo
     */
    public TipoGeneroEnum getNombre() {
        return TipoGeneroEnum.parse(this.nombre);
    }

    /**
     * @param estilo the estilo to set
     */
    public void setNombre(TipoGeneroEnum nombre) {
        this.nombre = nombre.getValue();
    }

}
