//package attorneygetrecommendation;
//
//import businessrule.gateway.AttorneyGateway;
//import businessrule.outputboundary.ViewOutputBoundary;
//import businessrule.responsemodel.ViewResponseModel;
//import entity.Attorney;
//import entity.Question;
//
//import java.util.List;
//
//public class AttorneyRecommendInteractor implements RecommendInputBoundary{
//    private final ViewOutputBoundary viewOutputBoundary;
//    private  final AttorneyGateway attorneyGateway;
//
//    public AttorneyRecommendInteractor(ViewOutputBoundary viewOutputBoundary, AttorneyGateway attorneyGateway) {
//        this.attorneyGateway = attorneyGateway;
//        this.viewOutputBoundary = viewOutputBoundary;
//    }
//
//    public ViewResponseModel getMatching(RecommendRequestModel recommendRequestModel) {
//        int attorneyId = recommendRequestModel.getAttorneyId();
//        Attorney attorney = (Attorney) attorneyGateway.getUser(attorneyId);
//        List<Question> recommendations = attorney.getRecommendations();
//
//        // TODO: complete the recommend response model call
//        if (! recommendations.isEmpty()) {
//            return viewOutputBoundary.prepareSuccess();
//        } else {
//            return viewOutputBoundary.prepareFail("You do not have recommendations!");
//        }
//    }
//}
