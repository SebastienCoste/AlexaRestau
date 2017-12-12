package scoste.restau.web.configuration;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import scoste.restau.web.WebConfig;
import scoste.restau.domain.event.EventTime;

import java.util.InputMismatchException;
import java.util.Optional;

public class EventTimeResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(EventTime.class);
    }

    @Override
    public EventTime resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        return Optional.ofNullable(nativeWebRequest.getHeader(WebConfig.EVENT_TIMESTAMP))
                .map(EventTime::new)
                .orElseThrow(() -> new InputMismatchException("Le header '" + WebConfig.EVENT_ID + "' doit être présent"));
    }
}