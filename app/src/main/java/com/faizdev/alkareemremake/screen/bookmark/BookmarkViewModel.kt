package com.faizdev.alkareemremake.screen.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faizdev.alkareemremake.data.repository.QuranRepository
import com.faizdev.alkareemremake.data.source.local.entity.Bookmark
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val repository: QuranRepository
): ViewModel() {

    val bookmarkState =
        repository.getBookmarkList().stateIn(
            viewModelScope, SharingStarted.WhileSubscribed(500), emptyList()
        )

    fun deleteBookmark(bookmark: Bookmark){
        viewModelScope.launch {
            repository.deleteBookmark(bookmark)
        }
    }

    fun onEvent(event: BookmarkScreenEvent) {
        when (event) {
            is BookmarkScreenEvent.DeleteAllBookmark -> {
                viewModelScope.launch {
                    repository.deleteAllBookmark()
                }
            }

            is BookmarkScreenEvent.DeleteBookmark -> {
                viewModelScope.launch {
                    repository.deleteBookmark(event.bookmark)
                }
            }

            is BookmarkScreenEvent.GetBookmark -> {
                viewModelScope.launch {
                    repository.getBookmarkList()
                }


            }

            else -> {}
        }

    }


}
sealed class BookmarkScreenEvent {
    object GetBookmark : BookmarkScreenEvent()


    data class DeleteBookmark(val bookmark: Bookmark) : BookmarkScreenEvent()

    data class DeleteAllBookmark(val bookmark: Bookmark) : BookmarkScreenEvent()

}