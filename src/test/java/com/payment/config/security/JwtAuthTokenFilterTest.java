package com.payment.config.security;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.payment.repository.UserRepository;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {JwtAuthTokenFilter.class, JwtProvider.class})
@ExtendWith(SpringExtension.class)
public class JwtAuthTokenFilterTest {
    @Autowired
    private JwtProvider jwtProvider;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testConstructor() {
        JwtAuthTokenFilter actualJwtAuthTokenFilter = new JwtAuthTokenFilter(this.jwtProvider, mock(UserRepository.class));
        assertTrue(actualJwtAuthTokenFilter
                .getEnvironment() instanceof org.springframework.web.context.support.StandardServletEnvironment);
        assertNull(actualJwtAuthTokenFilter.getFilterConfig());
    }

    @Test
    public void testDoFilterInternal() throws IOException, ServletException {
        JwtAuthTokenFilter jwtAuthTokenFilter = new JwtAuthTokenFilter(new JwtProvider(), mock(UserRepository.class));
        MockHttpServletRequest request = new MockHttpServletRequest();
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter((javax.servlet.ServletRequest) any(), (javax.servlet.ServletResponse) any());
        jwtAuthTokenFilter.doFilterInternal(request, response, filterChain);
        verify(filterChain).doFilter((javax.servlet.ServletRequest) any(), (javax.servlet.ServletResponse) any());
    }
}

