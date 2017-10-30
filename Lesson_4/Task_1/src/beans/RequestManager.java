package beans;

import util.sorter.requestsorter.*;
import entity.Request;
import repositories.RequestRepository;

import java.util.Arrays;

public class RequestManager {
    private RequestRepository requestRepository;

    public RequestManager(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }


    public RequestRepository getRequestRepository() {
        return requestRepository;
    }

    public Request[] sortRequestsByAlphabet(){
        Arrays.sort(requestRepository.getRequests(), new SortingRequestByAlphabet());
        return requestRepository.getRequests();
    }

    public Request[] sortRequestsByAmount(){
        Arrays.sort(requestRepository.getRequests(), new SortingRequestByAmount());
        return requestRepository.getRequests();
    }

}
