package main.services;

import main.data.dto.FoundPage;
import main.data.model.Site;
import main.data.model.Status;
import main.data.repository.SiteRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;

public class ResponseEntityLoader {

    public static ResponseEntity<JSONObject> getIndexingAlreadyStartResponse(){
        JSONParser parser = new JSONParser();
        JSONObject result = new JSONObject();

        try {
            result = (JSONObject) parser.parse("{\n\"result\": false,\n\"error\": \"Индексация уже запущена\"\n}");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(result,HttpStatus.FORBIDDEN);
    }

    public static ResponseEntity<JSONObject> getControllerMethodStartResponse(){
        JSONParser parser = new JSONParser();
        JSONObject result = new JSONObject();
        try {
            result = (JSONObject) parser.parse("{\n\"result\": true\n}");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    public static ResponseEntity<JSONObject> getIndexingNotStartResponse(){
        JSONParser parser = new JSONParser();
        JSONObject result = new JSONObject();

        try {
            result = (JSONObject) parser.parse("{\n\"result\": false,\n\"error\": \"Индексация не запущена\"\n}");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(result,HttpStatus.FORBIDDEN);
    }

    public static ResponseEntity<JSONObject> getPageOutOfRangeResponse(){
        JSONParser parser = new JSONParser();
        JSONObject result = new JSONObject();

        try {
            result = (JSONObject) parser.parse("{\n\"result\": false,\n\"error\": \"Данная страница находится за пределами сайтов,указанных в конфигурационном файле\"\n}");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<JSONObject> getEmptySearchQueryResponse(){
        JSONParser parser = new JSONParser();
        JSONObject result = new JSONObject();

        try {
            result = (JSONObject) parser.parse("{\n\"result\": false,\n\"error\": \"Отсутствует поисковый запрос\"\n}");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<JSONObject> getSiteNotFoundResponse(){
        JSONParser parser = new JSONParser();
        JSONObject result = new JSONObject();

        try {
            result = (JSONObject) parser.parse("{\n\"result\": false,\n\"error\": \"Запрашиваемый сайт отсутсвует в базе данных\"\n}");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<JSONObject> getIndexedSitesNotFoundResponse(){
        JSONParser parser = new JSONParser();
        JSONObject result = new JSONObject();

        try {
            result = (JSONObject) parser.parse("{\n\"result\": false,\n\"error\": \"Отсутсвуют проиндексированные сайты\"\n}");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<JSONObject> getSearchMatchesNotFoundResponse(){
        JSONParser parser = new JSONParser();
        JSONObject result = new JSONObject();

        try {
            result = (JSONObject) parser.parse("{\n\"result\": false,\n\"error\": \"Отсутсвуют совпадения\"\n}");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<JSONObject> getRelevantPagesNotFoundResponse(){
        JSONParser parser = new JSONParser();
        JSONObject result = new JSONObject();

        try {
            result = (JSONObject) parser.parse("{\n\"result\": false,\n\"error\": \"Отсутсвует вывод найденных совпадений\"\n}");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
    }


    public static ResponseEntity<JSONObject> getSiteIndexingOrEmptyPagesResponse(SiteRepository siteRepository, Site targetSite){
        JSONParser parser = new JSONParser();
        ResponseEntity<JSONObject> resultJson = null;
        try {
            resultJson = siteRepository.findById(targetSite.getId()).get().getStatus().compareTo(Status.INDEXING) == 0 ?
                    new ResponseEntity<> ((JSONObject) parser.parse("{\n\"result\": false,\n\"error\": \"Запрашиваемый сайт индексируется, попробуйте позже\"\n}"), HttpStatus.FORBIDDEN) :
                    new ResponseEntity<> ((JSONObject) parser.parse("{\n\"result\": false,\n\"error\": \"Запрашиваемый сайт не имеет страниц в базе данных\"\n}"), HttpStatus.NOT_FOUND);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return resultJson;
    }

    public static ResponseEntity<JSONObject> getSiteIndexingOrEmptyLemmasResponse(SiteRepository siteRepository, Site targetSite){
        JSONParser parser = new JSONParser();
        ResponseEntity<JSONObject> resultJson = null;
        try {
            resultJson = siteRepository.findById(targetSite.getId()).get().getStatus().compareTo(Status.INDEXING) == 0 ?
                    new ResponseEntity<> ((JSONObject) parser.parse("{\n\"result\": false,\n\"error\": \"Запрашиваемый сайт индексируется, попробуйте позже\"\n}"), HttpStatus.FORBIDDEN) :
                    new ResponseEntity<> ((JSONObject) parser.parse("{\n\"result\": false,\n\"error\": \"Запрашиваемый сайт не имеет лемм в базе данных\"\n}"), HttpStatus.NOT_FOUND);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return resultJson;
    }

    public static ResponseEntity<JSONObject> getSiteIndexingOrEmptyIndexesResponse(SiteRepository siteRepository, Site targetSite){
        JSONParser parser = new JSONParser();
        ResponseEntity<JSONObject> resultJson = null;
        try {
            resultJson = siteRepository.findById(targetSite.getId()).get().getStatus().compareTo(Status.INDEXING) == 0 ?
                    new ResponseEntity<> ((JSONObject) parser.parse("{\n\"result\": false,\n\"error\": \"Запрашиваемый сайт индексируется, попробуйте позже\"\n}"), HttpStatus.FORBIDDEN) :
                    new ResponseEntity<> ((JSONObject) parser.parse("{\n\"result\": false,\n\"error\": \"Запрашиваемый сайт не имеет индексов в базе данных\"\n}"), HttpStatus.NOT_FOUND);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return resultJson;
    }
}
