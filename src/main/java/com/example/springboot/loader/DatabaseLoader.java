package com.example.springboot.loader;

import com.example.springboot.constants.Roles;
import com.example.springboot.models.*;
import com.example.springboot.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final ChannelRepository channelRepository;
    private final VideoRepository videoRepository;
    private final CommentsRepository commentsRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DatabaseLoader(RoleRepository roleRepository,
                          VideoRepository videoRepository,
                          ChannelRepository channelRepository,
                          UserRepository userRepository,
                          CommentsRepository commentsRepository,PasswordEncoder passwordEncoder) {
        this.roleRepository=roleRepository;
        this.userRepository=userRepository;
        this.videoRepository=videoRepository;
        this.channelRepository=channelRepository;
        this.passwordEncoder = passwordEncoder;
        this.commentsRepository = commentsRepository;
    }
    @Override
    public void run(String... args) throws Exception {

        if(this.roleRepository.count()==0)
        {
            this.roleRepository.save(new Role(Roles.Admin));
            this.roleRepository.save(new Role(Roles.User));
        }

        if(this.userRepository.count()==0)
        {
//            this.userRepository.save(
//                    new User("semen@gmail.com",passwordEncoder.encode("123456"), "blabla")
//            );
        }

        User user=this.userRepository.findByName("vlad");
        if(this.channelRepository.count()==0)
        {
            this.channelRepository.save(
                    new Channel("animeMad","def.png",user)
            );
        }
        if(this.videoRepository.count()==0)
        {
            this.videoRepository.save(
                    new Video("1.mp4","Евангелион 1 серия 1 сезон","Рус. озвучка , видео взято с сайта jut.su","prev.jpeg",this.channelRepository.findById(1).get())
            );
            this.videoRepository.save(
                    new Video("2.mp4","Джоджо 1 серия 2 сезон","Рус. озвучка , видео взято с сайта jut.su","prev.jpeg",this.channelRepository.findById(1).get())
            );
        }
        Video video1=this.videoRepository.findById(1).get();
        Video video2=this.videoRepository.findById(2).get();
        if(this.commentsRepository.count()==0)
        {
            this.commentsRepository.save(
                    new Comment("i have depresion now! thanks!",user,video1)
            );
            this.commentsRepository.save(
                    new Comment("now im gay",user,video2)
            );
        }
    }
}