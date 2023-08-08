
package adapter.controller;

import businessrule.inputboundary.ClientRegisterInputBoundary;
import businessrule.requestmodel.ClientRegisterRequestModel;
import businessrule.responsemodel.RegisterResponseModel;

/**
 * This class represents a controller responsible for handling client registration. It interacts with the
 * business rules through the input boundary to register a new client.
 */

public class ClientRegisterControl {
    private final ClientRegisterInputBoundary inputBoundary;

    public ClientRegisterControl(ClientRegisterInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     * Creates a new client registration based on the provided input parameters.
     *
     * @param userName The username of the client.
     * @param email The email address of the client.
     * @param password1 The first password entered by the client.
     * @param password2 The confirmation password entered by the client.
     * @param StateAbb The state abbreviation where the client resides.
     * @param PostalCode The postal code of the client's location.
     * @param ethnicity The ethnicity of the client.
     * @param age The age of the client.
     * @param gender The gender of the client.
     * @param maritalStatus The marital status of the client.
     * @param numberOfHousehold The number of members in the client's household.
     * @param annualIncome The annual income of the client.
     * @return A {@link businessrule.responsemodel.RegisterResponseModel} indicating the result of the registration.
     */
    public RegisterResponseModel create(String userName, String email, String password1, String password2,
                                        String StateAbb, String PostalCode,
                                        String ethnicity, int age, String gender, String maritalStatus,
                                        int numberOfHousehold, float annualIncome) {
        ClientRegisterRequestModel requestModel = new ClientRegisterRequestModel(userName, email, password1, password2, StateAbb,
                PostalCode, ethnicity, age, gender, maritalStatus, numberOfHousehold, annualIncome);
        return inputBoundary.create(requestModel);
    }
}