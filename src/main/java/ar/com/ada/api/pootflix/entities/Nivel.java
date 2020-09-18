package ar.com.ada.api.pootflix.entities;

public class Nivel {
    public enum TipoNivelEnum {
        PRINCIPAL(1), SECUNDARIO(2), TERCIARIOF(3);

        private final Integer value;

        // NOTE: Enum constructor tiene que estar en privado
        private TipoNivelEnum(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public static TipoNivelEnum parse(Integer id) {
            TipoNivelEnum status = null; // Default
            for (TipoNivelEnum item : TipoNivelEnum.values()) {
                if (item.getValue().equals(id)) {
                    status = item;
                    break;
                }
            }
            return status;
        }
    }

}
