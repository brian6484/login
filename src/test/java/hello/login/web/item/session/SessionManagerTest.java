package hello.login.web.item.session;

import hello.login.domain.member.Member;
import hello.login.web.session.SessionManager;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.*;

class SessionManagerTest {

    SessionManager sessionManager = new SessionManager();

    @Test
    void sessionTest(){
//        create session
        MockHttpServletResponse response = new MockHttpServletResponse();

        Member member = new Member();
        sessionManager.createSession(member,response);

//        store request cookie
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());

//      search session
        Object result = sessionManager.getSession(request);
        assertThat(result).isEqualTo(member);

//        end session
        sessionManager.expire(request);
        Object expired = sessionManager.getSession(request);
        assertThat(expired).isNull();

    }

}