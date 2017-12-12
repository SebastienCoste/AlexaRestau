package scoste.restau.web.configuration;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import scoste.restau.web.WebConfig;
import scoste.restau.domain.event.EventId;

import java.util.InputMismatchException;
import java.util.Optional;

public class EventIdResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(EventId.class);
    }

    @Override
    public EventId resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        return Optional.ofNullable(nativeWebRequest.getHeader(WebConfig.EVENT_ID))
                .map(EventId::new)
                .orElseThrow(() -> new InputMismatchException("Le header '" + WebConfig.EVENT_ID + "' doit être présent"));
    }
}