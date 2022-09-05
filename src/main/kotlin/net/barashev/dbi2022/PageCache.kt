/*
 * Copyright 2022 Dmitry Barashev, JetBrains s.r.o.
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

package net.barashev.dbi2022

interface CachedPage : AutoCloseable {
    val diskPage: DiskPage
}

interface PageCache {
    fun load(startPageId: PageId, pageCount: Int = 1)

    fun get(pageId: PageId): CachedPage

    fun getAndPin(pageId: PageId): CachedPage
    fun createSubCache(size: Int): PageCache
    fun flush()
    val stats: PageCacheStats
}

interface PageCacheStats {
    val cacheHit: Int
    val cacheMiss: Int
}