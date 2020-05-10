package dev.forcetower.podcasts.service.music

import android.net.Uri
import android.os.Bundle
import android.os.ResultReceiver
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.PlaybackStateCompat
import com.google.android.exoplayer2.ControlDispatcher
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector
import com.google.android.exoplayer2.upstream.DataSource
import dev.forcetower.podcasts.core.extensions.album
import dev.forcetower.podcasts.core.extensions.id
import dev.forcetower.podcasts.core.extensions.toMediaSource
import dev.forcetower.podcasts.core.extensions.trackNumber
import timber.log.Timber

class MusicPlaybackPreparer(
    private val musicSource: MusicSource,
    private val exoPlayer: ExoPlayer,
    private val dataSourceFactory: DataSource.Factory
) : MediaSessionConnector.PlaybackPreparer {
    override fun onCommand(
        player: Player,
        controlDispatcher: ControlDispatcher,
        command: String,
        extras: Bundle,
        cb: ResultReceiver
    ) = false

    override fun getSupportedPrepareActions() =
        PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID or
                PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID or
                PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH or
                PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH

    override fun onPrepareFromMediaId(mediaId: String, playWhenReady: Boolean, extras: Bundle) {
        Timber.d("onPrepareFromMediaId $mediaId, $playWhenReady")
        musicSource.whenReady {
            val itemToPlay: MediaMetadataCompat? = musicSource.find { item ->
                item.id == mediaId
            }
            if (itemToPlay == null) {
                Timber.e("Impossible to play a song i don't have :)")
                // TODO: Notify caller of the error.
            } else {
                Timber.d("Found item to play $itemToPlay")
                val metadataList = buildPlaylist(itemToPlay)
                val mediaSource = metadataList.toMediaSource(dataSourceFactory)

                val initialWindowIndex = metadataList.indexOf(itemToPlay)
                val position = extras.getLong(MusicService.SEEK_POSITION, 0)
                Timber.d("Received start position $position")
                exoPlayer.playWhenReady = playWhenReady
                exoPlayer.prepare(mediaSource)
                exoPlayer.seekTo(initialWindowIndex, position)
            }
        }
    }

    override fun onPrepareFromUri(uri: Uri, playWhenReady: Boolean, extras: Bundle) {
        Timber.w("User tried to make me prepare for Uri, I said nothing :)")
    }

    override fun onPrepareFromSearch(query: String, playWhenReady: Boolean, extras: Bundle) {
        musicSource.whenReady {
            val metadataList = musicSource.search(query, extras)
            if (metadataList.isNotEmpty()) {
                val mediaSource = metadataList.toMediaSource(dataSourceFactory)
                exoPlayer.playWhenReady = playWhenReady
                exoPlayer.prepare(mediaSource)
            }
        }
    }

    override fun onPrepare(playWhenReady: Boolean) {
        Timber.d("On general prepare $playWhenReady")
    }

    private fun buildPlaylist(item: MediaMetadataCompat): List<MediaMetadataCompat> =
        musicSource.filter { it.album == item.album }.sortedByDescending { it.trackNumber }
}