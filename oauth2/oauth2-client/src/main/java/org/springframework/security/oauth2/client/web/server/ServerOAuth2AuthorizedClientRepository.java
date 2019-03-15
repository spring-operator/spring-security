/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.security.oauth2.client.web.server;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Implementations of this interface are responsible for the persistence
 * of {@link OAuth2AuthorizedClient Authorized Client(s)} between requests.
 *
 * <p>
 * The primary purpose of an {@link OAuth2AuthorizedClient Authorized Client}
 * is to associate an {@link OAuth2AuthorizedClient#getAccessToken() Access Token} credential
 * to a {@link OAuth2AuthorizedClient#getClientRegistration() Client} and Resource Owner,
 * who is the {@link OAuth2AuthorizedClient#getPrincipalName() Principal}
 * that originally granted the authorization.
 *
 * @author Rob Winch
 * @since 5.1
 * @see OAuth2AuthorizedClient
 * @see ClientRegistration
 * @see Authentication
 * @see OAuth2AccessToken
 */
public interface ServerOAuth2AuthorizedClientRepository {

	/**
	 * Returns the {@link OAuth2AuthorizedClient} associated to the
	 * provided client registration identifier and End-User {@link Authentication} (Resource Owner)
	 * or {@code null} if not available.
	 *
	 * @param clientRegistrationId the identifier for the client's registration
	 * @param principal the End-User {@link Authentication} (Resource Owner)
	 * @param exchange the {@code ServerWebExchange}
	 * @param <T> a type of OAuth2AuthorizedClient
	 * @return the {@link OAuth2AuthorizedClient} or {@code null} if not available
	 */
	<T extends OAuth2AuthorizedClient> Mono<T> loadAuthorizedClient(String clientRegistrationId,
			Authentication principal, ServerWebExchange exchange);

	/**
	 * Saves the {@link OAuth2AuthorizedClient} associating it to
	 * the provided End-User {@link Authentication} (Resource Owner).
	 *
	 * @param authorizedClient the authorized client
	 * @param principal the End-User {@link Authentication} (Resource Owner)
	 * @param exchange the {@code ServerWebExchange}
	 */
	Mono<Void> saveAuthorizedClient(OAuth2AuthorizedClient authorizedClient,
			Authentication principal, ServerWebExchange exchange);

	/**
	 * Removes the {@link OAuth2AuthorizedClient} associated to the
	 * provided client registration identifier and End-User {@link Authentication} (Resource Owner).
	 *
	 * @param clientRegistrationId the identifier for the client's registration
	 * @param principal the End-User {@link Authentication} (Resource Owner)
	 * @param exchange the {@code ServerWebExchange}
	 */
	Mono<Void> removeAuthorizedClient(String clientRegistrationId, Authentication principal,
			ServerWebExchange exchange);

}
