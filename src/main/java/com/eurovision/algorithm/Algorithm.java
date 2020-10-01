package com.eurovision.algorithm;

import com.eurovision.model.City;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
    In this algorithm the aproach will be start from the first city to the last, going forwards instead of going backwards. From A to Z.

    We will be using the list of Cities provided by the repository, they have to be already in alphabetical order for the algorithm to work.
    Also we will be using a custom object called node that will provide us of the object city itself, the id (city id) and the nextNode field, which will tell us the the next city id after this node.
    Thanks to this we will be able to know if a city id can be placed in the map. Example:NewNode with CityId = 255, Node with CityId 262 and NextNode with CityId 157, we know NewNode can replace the first
    (because its smaller than the first node and bigger than the next node).
    We will be creating sequences from 1 to N and updating sequences so that the N sequence biggest value is the smallest possible.

 */
public class Algorithm {

    private List<City> cities;

    public Algorithm(List<City> cities) {
        this.cities = cities;
    }

    private class Node {

        private City city;

        private Integer cityId;

        private Node nextNode;

        public Node(City city) {
            this.city = city;
            cityId = city.getId();
        }
        public Node getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }

    }

    public List<City> runAlgorithm(){

        //List only to return the result with the longest N in a city sequence
        List<City> result = new ArrayList<>();

        //Map in which we have the distint cities sequences from 1 to N
        HashMap<Integer,List<City>> valuesSequenceMap = new HashMap<Integer,List<City>>();

        //Map that will keep the first (or biggest) value of the sequences from 1 to N
        HashMap<Integer,Node> firstValueOfSequencesMap = new HashMap<Integer,Node>();

        //Smallest city id that has appeared, we will suppose that the Ids are all positive and smallest possible is 1
        Integer smallestCityId = 0;

        for(City city : cities){

            Integer cityId = city.getId();

            //This boolean value will tell us if the city has found its place in any sequence
            Boolean changed = false;

            //In case its the first city or we find a new smallest Id
            if(!valuesSequenceMap.isEmpty() && cityId>smallestCityId){

                //We run our firstValueMap in order to see if we can fit this id, we run it backwards from N to 1
                for(int i = firstValueOfSequencesMap.size()-1;i>=0  && !changed;i--){


                    Integer sequenceValue = (Integer) firstValueOfSequencesMap.keySet().toArray()[i];

                    //We get N value for the sequence

                    //First we check if the city can replace a first value in the map Example:NewNode with CityId = 255, Node with CityId 262 and NextNode with CityId 157, we know NewNode can replace the first
                    //We replace the node with new node for the sequence value
                    //We replace the last city of the list in the cities sequence map for the sequence value
                    if(cityId<firstValueOfSequencesMap.get(sequenceValue).cityId && cityId>firstValueOfSequencesMap.get(sequenceValue).nextNode.cityId) {

                        changed = true;

                        setUpFirstValuesSequenceMap(firstValueOfSequencesMap,city,true,sequenceValue,firstValueOfSequencesMap.get(sequenceValue).nextNode);
                        replaceLastCitySequenceMap(valuesSequenceMap,sequenceValue,city);
                    }
                    // In case the cityId is bigger than the cityId of a first node of a N sequence value
                    else if(cityId>firstValueOfSequencesMap.get(sequenceValue).cityId){


                        Integer newValue = sequenceValue +1;

                        //We add one to the sequenceValue Example: N-> 2 cityId : 262 and first value is 157, then N +1 -> 3

                        //In case there is already a Sequence for that value N +1 , we check that the new cityId is smaller than the firstValue for the N +1 sequence
                        //We replace the node with new node for the sequence value
                        //We replace the list of the cities sequence map for the sequence value N + 1, with the cities sequence of N and we add the new city
                        if(firstValueOfSequencesMap.containsKey(newValue) && cityId < firstValueOfSequencesMap.get(newValue).cityId){

                            changed = true;

                            setUpFirstValuesSequenceMap(firstValueOfSequencesMap,city,true,newValue,firstValueOfSequencesMap.get(sequenceValue));
                            List<City> listCities =new ArrayList<>(valuesSequenceMap.get(sequenceValue));
                            setUpSequenceMapForNewList(valuesSequenceMap,listCities,city,true,newValue);
                        }
                        //In case there is no Sequence for that value N +1
                        //We put the node with new node for the sequence value
                        //We put the list of the cities sequence map for the sequence value N + 1, with the cities sequence of N and we add the new city
                        else if(!firstValueOfSequencesMap.containsKey(newValue)){

                            changed = true;

                            setUpFirstValuesSequenceMap(firstValueOfSequencesMap,city,false,newValue,firstValueOfSequencesMap.get(sequenceValue));
                            List<City> listCities = new ArrayList<>(valuesSequenceMap.get(sequenceValue));
                            setUpSequenceMapForNewList(valuesSequenceMap,listCities,city,false,newValue);

                        }
                    }
                }

            }
            else{
                //We update the maps for N =1  for the new smallest cityId
                if(valuesSequenceMap.containsKey(1)){
                    replaceLastCitySequenceMap(valuesSequenceMap,1,city);
                    setUpFirstValuesSequenceMap(firstValueOfSequencesMap,city,true,1,null);
                }
                else{
                    setUpSequenceMapForNewList(valuesSequenceMap,new ArrayList<>(),city,false,1);
                    setUpFirstValuesSequenceMap(firstValueOfSequencesMap,city,false,1,null);
                }

                smallestCityId = cityId;
            }
        }

        //We set up the result with the cities sequence for the hightest N
        result = valuesSequenceMap.get(valuesSequenceMap.size());

        return result;
    }

    private void setUpFirstValuesSequenceMap(HashMap<Integer,Node> pFirstValuesMap, City pCity, Boolean pReplace, Integer pValueKey, Node pNextNode){

        Node newNode = new Node(pCity);
        newNode.setNextNode(pNextNode);
        if(pReplace){
            pFirstValuesMap.replace(pValueKey,newNode);
        }
        else{
            pFirstValuesMap.put(pValueKey,newNode);
        }

    }

    private void replaceLastCitySequenceMap(HashMap<Integer,List<City>> pSequenceList, Integer pValueKey, City pCityToAdd){

        List<City> list = pSequenceList.get(pValueKey);
        list.remove(list.get(list.size()-1));
        list.add(pCityToAdd);

    }

    private void setUpSequenceMapForNewList(HashMap<Integer,List<City>> pSequenceList, List<City> pNewList, City pCity, Boolean pReplace, Integer pValueKey){

        pNewList.add(pCity);
        if(pReplace){
            pSequenceList.replace(pValueKey,pNewList);
        }
        else{
            pSequenceList.put(pValueKey,pNewList);
        }

    }

}

