package com.beauty4u.backend.noti.command.domain.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class SseRepository {

    /* SSE 연결을 저장하는 맵 */
    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();
    /* 이벤트 캐시를 저장하는 맵 (연결이 끊긴 클라이언트를 위한 이벤트 저장)*/
    private final Map<String, Object> eventCache = new ConcurrentHashMap<>();

    /* 클라이언트의 SSE 연결을 저장 */
    public SseEmitter save(String emitterId, SseEmitter sseEmitter) {

        emitters.put(emitterId, sseEmitter);
        return sseEmitter;
    }

    /*
     * 클라이언트의 이벤트를 캐시에 저장
     * 클라이언트가 일시적으로 연결이 끊겼을 때를 대비하여 이벤트를 캐시함
     */
    public void saveEventCache(String emitterId, Object event) {

        eventCache.put(emitterId, event);
    }

    /* 특정 회원코드로 시작하는 모든 SSE 연결을 조회 */
    public Map<String, SseEmitter> findAllEmitterStartWithByUserCode(String userCode) {

        return emitters.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(userCode))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /* 특정 회원코드로 시작하는 모든 이벤트 캐시를 조회 */
    public Map<String, Object> findAllEventCacheStartWithByUserCode(String userCode) {

        return eventCache.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(userCode))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /* 특정 클라이언트의 SSE 연결을 삭제 */
    public void deleteById(String emitterId) {

        emitters.remove(emitterId);
    }

    /*
     * 특정 회원코드로 시작하는 모든 SSE 연결 삭제
     * 사용자가 로그아웃하거나 세션이 종료될 때 호출됨
     */
    public void deleteAllEmitterStartWithByUserCode(String userCode) {

        emitters.forEach(
                (key, emitter) -> {
                    if (key.startsWith(userCode)) {
                        emitters.remove(key);
                    }
                }
        );
    }

    /*
     * 특정 회원코드로 시작하는 모든 이벤트 캐시를 삭제
     * 더 이상 필요하지 않은 캐시된 이벤트들을 정리할 때 사용됨
     */
    public void deleteAllEventCacheStartWithByUserCode(String userCode) {

        eventCache.forEach(
                (key, value) -> {
                    if (key.startsWith(userCode)) {
                        eventCache.remove(key);
                    }
                }
        );
    }
}
