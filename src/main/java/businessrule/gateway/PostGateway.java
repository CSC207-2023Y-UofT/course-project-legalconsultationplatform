package businessrule.gateway;

import entity.Post;
import entity.Question;
import entity.User;

public interface PostGateway extends GenericGateway<Post>{

    @Override
    Post get(int id);

}
