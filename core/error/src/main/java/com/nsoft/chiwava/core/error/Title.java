/*
 * Copyright 2019 NSoft
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nsoft.chiwava.core.error;

/**
 * {@link Error} title abstraction
 *
 * <pre>
 *     public enum ExceptionTitles implements Title {
 *          RESOURCE_NOT_FOUND("exception.resource_not_found")
 *         ;
 *
 *         private final String title;
 *
 *         ErrorTitles(String title) {
 *             this.title = title;
 *         }
 *
 *         &#64;Override
 *         public String toString() {
 *             return this.title;
 *         }
 *     }
 * </pre>
 *
 * @author Mislav Milicevic
 * @since 2019-09-06
 */
public interface Title {

    String toString();
}
