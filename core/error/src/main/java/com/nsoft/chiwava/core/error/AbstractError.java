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
 * Abstract implementation of {@link Error}
 *
 * <pre>
 *     public class ResourceNotFoundException extends AbstractError {
 *
 *         &#64;Override
 *         public Status getStatus() {
 *             return Status.NOT_FOUND;
 *         }
 *
 *         &#64;Override
 *         public Title getTitle() {
 *             return ErrorTitles.RESOURCE_NOT_FOUND;
 *         }
 *     }
 * </pre>
 *
 * @author Mislav Milicevic
 * @since 2019-09-06
 */
public abstract class AbstractError extends RuntimeException implements Error {
    public AbstractError() {
        super();
    }

    public AbstractError(String message) {
        super(message);
    }
}
