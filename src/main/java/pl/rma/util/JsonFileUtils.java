package pl.rma.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import pl.rma.model.Reklamacja;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonFileUtils {

    private static final ObjectMapper mapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);

    public static void zapiszReklamacjeDoPliku(List<Reklamacja> lista, String sciezkaPliku) throws IOException {
        mapper.writeValue(new File(sciezkaPliku), lista);
    }

    public static List<Reklamacja> wczytajReklamacjeZPliku(String sciezkaPliku) throws IOException {
        return mapper.readValue(new File(sciezkaPliku),
                mapper.getTypeFactory().constructCollectionType(List.class, Reklamacja.class));
    }
}

