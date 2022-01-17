package com.example.musicplayer

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.musicplayer.databinding.FragmentMainBinding
import com.example.musicplayer.factory.MusicViewFactory
import com.example.musicplayer.repository.MusicRepository
import com.example.musicplayer.viewmodel.MusicViewModel
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var fragmentBinding : FragmentMainBinding? = null
    private var player : SimpleExoPlayer? = null
    private var songurl: String? = null

    //백그라운드 재생방지 변수 선언언
   private var playbackPosition = 0L // 현재 곡의 재생시간
    private var currentWindow = 0 // 재생목록 중의 현재 곡 순번
    private var playWhenReady = false // 재생을 자동으로 할것인지 아닌지


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentBinding = FragmentMainBinding.inflate(inflater,container,false)
        return fragmentBinding!!.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        val factory = MusicViewFactory(MusicRepository());
        val viewModel = ViewModelProvider(this, factory).get(MusicViewModel::class.java);
        viewModel.searchMusicList();

        //viewmodel
        viewModel.data.observe(viewLifecycleOwner, Observer {
            fragmentBinding!!.textviewTitle.setText(it.title)
            fragmentBinding!!.textviewSinger.setText(it.singer)

            Glide.with(this).load(it.image).into(fragmentBinding!!.imageviewImg);
            initializePlayer(it.file)
        })

    }

    override fun onPause() {
        super.onPause()
        releasePlayer()
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }
    private fun initializePlayer(songurl: String?) {
        if (player == null) {
            player = SimpleExoPlayer.Builder(requireContext()).build()
            fragmentBinding!!.playercontrolview.player = player
            fragmentBinding!!.playercontrolview.showTimeoutMs = 0//사라지지않게함


            val factory = DefaultDataSourceFactory(requireContext(), getString(R.string.app_name));


            val mediaItem = MediaItem.fromUri(Uri.parse(songurl));

            val mediaSource = ProgressiveMediaSource.Factory(factory).createMediaSource(mediaItem);
            player!!.setMediaSource(mediaSource)
            player!!.seekTo(currentWindow, playbackPosition)
            player!!.playWhenReady = playWhenReady
        }
    }


    private fun releasePlayer() {
        player?.let {
            playbackPosition = it.currentPosition
            currentWindow = it.currentWindowIndex
            playWhenReady = it.playWhenReady
            it.release()
            player = null
        }
    }

}