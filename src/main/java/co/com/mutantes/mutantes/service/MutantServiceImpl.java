package co.com.mutantes.mutantes.service;

import co.com.mutantes.mutantes.model.Stats;
import co.com.mutantes.mutantes.repository.StatsRepository;
import co.com.mutantes.mutantes.utils.GeneralUtils;
import co.com.mutantes.mutantes.utils.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class MutantServiceImpl implements mutantService{

    @Autowired
    private Parameters parameters;
    @Autowired
    private GeneralUtils generalUtils;
    @Autowired
    private StatsRepository statsRepository;

    @Override
    public ResponseEntity<Object> isMutant(List<String> dna){
        int countSequence = 0;
        int sizeColumn = 0;
        sizeColumn = Arrays.asList(dna.get(0).split("")).size();
        String [][] matrix = convertListToMatrix(dna);
        countSequence = analyzeHorizontal(dna,countSequence);
        countSequence = analyzeVertical(sizeColumn,matrix,countSequence);
        countSequence = analyzeMainDiagonals(matrix,countSequence);
        countSequence = analyzeSecondaryDiagonals(matrix, countSequence);
        return buildResponse(countSequence);
    }

    private  ResponseEntity<Object> buildResponse(int countSequence){
        Stats stats = statsRepository.getStats();
        if (countSequence > 1){
            updateCountMutant(stats);
            return new ResponseEntity<>(generalUtils.buildMessage("true"), HttpStatus.FORBIDDEN);
        }else {
            updateCountHuman(stats);
            return new ResponseEntity<>(generalUtils.buildMessage("false"), HttpStatus.OK);
        }
    }

    private int analyzeMainDiagonals(String [][] matrix,  int countSequence){
        StringBuilder infoDiagonal;
        for( int k = 0 ; k < matrix.length * 2 ; k++ ) {
            infoDiagonal = new StringBuilder();
            for( int j = 0 ; j <= k ; j++ ) {
                int i = k - j;
                if( i < matrix.length && j < matrix.length ) {
                    infoDiagonal.append(matrix[i][j]);
                }
            }
            if (isSequenceMutant(infoDiagonal.toString())){
                countSequence++;
            }
        }
        return countSequence;
    }

    private int analyzeSecondaryDiagonals(String [][] matrix,  int countSequence){
        StringBuilder infoSecondaryDiagonal =   null;
        for (int i = matrix.length - 1; i > 0; i--) {
            infoSecondaryDiagonal = new StringBuilder();
            for (int j = 0, x = i; x <= matrix.length - 1; j++, x++) {
                infoSecondaryDiagonal.append(matrix[x][j]);
            }
            if (isSequenceMutant(infoSecondaryDiagonal.toString())){
                countSequence++;
            }
        }
        for (int i = 0; i <= matrix.length - 1; i++) {
            infoSecondaryDiagonal = new StringBuilder();
            for (int j = 0, y = i; y <= matrix.length - 1; j++, y++) {
                infoSecondaryDiagonal.append(matrix[j][y]);
            }
            if (isSequenceMutant(infoSecondaryDiagonal.toString())){
                countSequence++;
            }
        }
        return countSequence;
    }

    private int analyzeVertical(int sizeColumn,String [][] matrix, int countSequence  ){
        StringBuilder  column = null;
        for (int k=0; k<sizeColumn; k++){
            column = new StringBuilder();
            for (int i=0;i<matrix.length;i++){
                column.append(matrix[i][k]);
            }
            if(isSequenceMutant(column.toString())){
                countSequence++;
            }
        }
        return  countSequence;
    }

    private int analyzeHorizontal(List<String> dna, int countSequence ){
        for (String data:dna) {
                if (isSequenceMutant(data)){
                    countSequence++;
                }
        }
        return  countSequence;
    }

    private boolean isSequenceMutant(String data){
        if(data.length() >= parameters.sizeSequence && isValidSequence(data)){
            List<String> listSequences = parameters.listSequenceMutant;
            for (String sequence:listSequences) {
                if(data.contains(sequence)){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean isValidSequence(String sequence) {
        String regex = parameters.regex;
        return Pattern.matches(regex, sequence);
    }


    private  String [][] convertListToMatrix(List<String> dna){
        List<Object> newList = new ArrayList<>();
        for (String data:dna) {
            Object newObj = data.split("");
            newList.add(newObj);
        }
       return newList.toArray(new String[0][]);
    }

    private void updateCountMutant( Stats stats){
       if(null !=stats.count_mutant_dna){
           stats.count_mutant_dna = stats.count_mutant_dna +1;
       }else{
           stats.count_mutant_dna = 1;
       }
       statsRepository.updateStats(stats);
    }

    private void updateCountHuman( Stats stats){
        if(null !=  stats.count_human_dna ){
            stats.count_human_dna = stats.count_human_dna +1;
        }else{
            stats.count_human_dna = 1;
        }
        statsRepository.updateStats(stats);
    }

}
